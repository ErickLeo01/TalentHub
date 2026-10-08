package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyExistingEmailORCNPJException;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotUpdateException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public CompanyResponseDTO updateCompany(UUID id, CompanyDTO companyDTO) {
        if (id == null || companyDTO == null) {
            throw new CompanyNotUpdateException();
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority -> "ROLE_COMPANY".equals(authority.getAuthority()))) {
            throw new CompanyNotUpdateException();
        }

        final UUID authenticatedCompanyId;
        try {
            authenticatedCompanyId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new CompanyNotUpdateException();
        }

        if (!authenticatedCompanyId.equals(id)) {
            throw new CompanyNotUpdateException();
        }

        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(CompanyNotUpdateException::new);

        if (companyRepository.existsByEmailAndIdNot(companyDTO.email(), id)
                || companyRepository.existsByCnpjAndIdNot(companyDTO.cnpj(), id)) {
            throw new CompanyExistingEmailORCNPJException();
        }

        companyEntity.setName(companyDTO.name());
        companyEntity.setEmail(companyDTO.email());
        companyEntity.setPassword(passwordEncoder.encode(companyDTO.password()));
        companyEntity.setCnpj(companyDTO.cnpj());
        companyEntity.setDescription(companyDTO.description());
        companyEntity.setAddress(companyDTO.address());

        CompanyEntity updatedCompany = companyRepository.save(companyEntity);

        return new CompanyResponseDTO(
                updatedCompany.getName(),
                updatedCompany.getEmail(),
                updatedCompany.getDescription(),
                updatedCompany.getAddress()
        );
    }
}
