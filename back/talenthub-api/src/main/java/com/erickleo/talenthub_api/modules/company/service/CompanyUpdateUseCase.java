package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyUpdateDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyExistingEmailORCNPJException;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotUpdateException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class CompanyUpdateUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyUpdateUseCase(
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CompanyResponseDTO updateCompany(
            UUID id,
            @Valid CompanyUpdateDTO companyDTO
    ) {
        if (id == null || companyDTO == null) {
            throw new CompanyNotUpdateException();
        }

        // Obtém os dados da empresa autenticada.
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_COMPANY".equals(authority.getAuthority()))) {
            throw new CompanyNotUpdateException();
        }

        // Converte o ID autenticado para UUID.
        final UUID authenticatedCompanyId;

        try {
            authenticatedCompanyId =
                    UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new CompanyNotUpdateException();
        }

        // Impede que uma empresa atualize o cadastro de outra.
        if (!authenticatedCompanyId.equals(id)) {
            throw new CompanyNotUpdateException();
        }

        // Busca a empresa que será atualizada.
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(CompanyNotUpdateException::new);

        // Verifica se o e-mail pertence a outra empresa.
        if (companyDTO.email() != null
                && companyRepository.existsByEmailAndIdNot(
                companyDTO.email(), id)) {
            throw new CompanyExistingEmailORCNPJException();
        }

        // Verifica se o CNPJ pertence a outra empresa.
        if (companyDTO.cnpj() != null
                && companyRepository.existsByCnpjAndIdNot(
                companyDTO.cnpj(), id)) {
            throw new CompanyExistingEmailORCNPJException();
        }

        // Atualiza somente os campos informados.
        if (companyDTO.name() != null) {
            companyEntity.setName(companyDTO.name());
        }

        if (companyDTO.email() != null) {
            companyEntity.setEmail(companyDTO.email());
        }

        // Altera a senha somente quando uma nova senha é informada.
        if (companyDTO.password() != null
                && !companyDTO.password().isBlank()) {
            companyEntity.setPassword(
                    passwordEncoder.encode(companyDTO.password())
            );
        }

        if (companyDTO.cnpj() != null) {
            companyEntity.setCnpj(companyDTO.cnpj());
        }

        if (companyDTO.description() != null) {
            companyEntity.setDescription(companyDTO.description());
        }

        if (companyDTO.address() != null) {
            companyEntity.setAddress(companyDTO.address());
        }

        // Salva as alterações.
        CompanyEntity updatedCompany =
                companyRepository.save(companyEntity);

        // Retorna os dados atualizados sem expor a senha.
        return new CompanyResponseDTO(
                updatedCompany.getId(),
                updatedCompany.getName(),
                updatedCompany.getEmail(),
                updatedCompany.getDescription(),
                updatedCompany.getAddress(),
                updatedCompany.getCnpj()
        );
    }
}