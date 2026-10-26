package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@Service
public class CompanyDeleteUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public String deleteCompany(UUID idCompany) {

        // Verifica se o ID foi informado.
        if (idCompany == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O ID da empresa é obrigatório."
            );
        }

        // Obtém a autenticação atual.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se o usuário está autenticado e possui o perfil correto.
        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_COMPANY".equals(authority.getAuthority()))) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para excluir uma empresa."
            );
        }

        // Converte o ID da empresa autenticada.
        UUID companyId;

        try {
            companyId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Não foi possível identificar a empresa autenticada."
            );
        }

        // Busca a empresa que será excluída.
        CompanyEntity companyEntity = companyRepository.findById(idCompany)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empresa não encontrada."
                ));

        // Impede a exclusão do cadastro de outra empresa.
        if (!companyEntity.getId().equals(companyId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não pode excluir outra empresa."
            );
        }

        // Exclui a própria empresa.
        companyRepository.delete(companyEntity);

        return "Empresa deletada com sucesso.";
    }
}