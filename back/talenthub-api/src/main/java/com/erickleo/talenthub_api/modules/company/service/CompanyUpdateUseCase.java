package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyUpdateDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyExistingEmailORCNPJException;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotUpdateException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
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
            CompanyUpdateDTO companyDTO
    ) {
        if (id == null || companyDTO == null) {
            throw new CompanyNotUpdateException();
        }

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

        final UUID authenticatedCompanyId;

        try {
            authenticatedCompanyId =
                    UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new CompanyNotUpdateException();
        }

        if (!authenticatedCompanyId.equals(id)) {
            throw new CompanyNotUpdateException();
        }

        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(CompanyNotUpdateException::new);

        // Verifica se o e-mail informado já pertence a outra empresa.
        if (companyDTO.email() != null
                && companyRepository.existsByEmailAndIdNot(
                companyDTO.email(), id)) {
            throw new CompanyExistingEmailORCNPJException();
        }

        // Verifica se o CNPJ informado já pertence a outra empresa.
        if (companyDTO.cnpj() != null
                && companyRepository.existsByCnpjAndIdNot(
                companyDTO.cnpj(), id)) {
            throw new CompanyExistingEmailORCNPJException();
        }

        // Atualiza somente os campos enviados.
        if (companyDTO.name() != null) {
            companyEntity.setName(companyDTO.name());
        }

        if (companyDTO.email() != null) {
            companyEntity.setEmail(companyDTO.email());
        }

        // Codifica a senha somente quando uma nova senha é informada.
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

        CompanyEntity updatedCompany =
                companyRepository.save(companyEntity);

        return new CompanyResponseDTO(
                updatedCompany.getName(),
                updatedCompany.getEmail(),
                updatedCompany.getDescription(),
                updatedCompany.getAddress()
        );
    }
}