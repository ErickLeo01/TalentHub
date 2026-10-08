package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyDeleteUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public String deleteCompany(UUID idCompany) {

        CompanyEntity companyEntity =
                companyRepository.findById(idCompany).orElse(null);

        if (companyEntity == null) {
            return "Não foi possível deletar a empresa. Verifique se esse ID existe.";
        }

        // Pega o UUID da empresa autenticada pelo JWT.
        String userId = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UUID companyId = UUID.fromString(userId);

        // Verifica se a empresa está tentando deletar o próprio cadastro.
        if (!companyEntity.getId().equals(companyId)) {
            return "Você não pode deletar outra empresa.";
        }

        companyRepository.delete(companyEntity);

        return "Empresa deletada com sucesso.";
    }
}