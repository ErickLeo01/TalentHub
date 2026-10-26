package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class CompanyFindByIdUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    // Busca uma empresa pelo ID.
    public CompanyResponseDTO findCompanyById(UUID companyId) {

        // Verifica se o ID foi informado.
        if (companyId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O ID da empresa é obrigatório."
            );
        }

        // Procura a empresa no banco de dados.
        CompanyEntity company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empresa não encontrada."
                ));

        // Retorna os dados da empresa, sem expor a senha.
        return new CompanyResponseDTO(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getDescription(),
                company.getAddress(),
                company.getCnpj()
        );
    }
}
