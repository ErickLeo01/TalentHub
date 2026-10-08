package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyExistingEmailORCNPJException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyCreateUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyResponseDTO createCompany(@Valid CompanyDTO companyDTO) {

        if (companyRepository.existsByEmail(companyDTO.email()) ||
                companyRepository.existsByCnpj(companyDTO.cnpj())) {

            throw new CompanyExistingEmailORCNPJException();
        }

        CompanyEntity companyEntity = new CompanyEntity();

        companyEntity.setName(companyDTO.name());
        companyEntity.setEmail(companyDTO.email());
        companyEntity.setPassword(passwordEncoder.encode(companyDTO.password()));
        companyEntity.setCnpj(companyDTO.cnpj());
        companyEntity.setDescription(companyDTO.description());
        companyEntity.setAddress(companyDTO.address());

        CompanyEntity savedCompany = companyRepository.save(companyEntity);

        return new CompanyResponseDTO(
                savedCompany.getName(),
                savedCompany.getEmail(),
                savedCompany.getDescription(),
                savedCompany.getAddress()
        );
    }
}