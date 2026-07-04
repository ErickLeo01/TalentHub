package com.erickleo.talentHub.useCases.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import com.erickleo.talentHub.dtos.company.CompanyResponseDTO;
import com.erickleo.talentHub.entities.CompanyEntity;
import com.erickleo.talentHub.exceptions.company.CompanyIdNotFoundException;
import com.erickleo.talentHub.repositories.CompanyRepository;

@Service
public class ShowCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyResponseDTO showCompany(UUID idCompany) {

        CompanyEntity companyEntity = companyRepository.findById(idCompany)
                .orElseThrow(CompanyIdNotFoundException::new);

        return new CompanyResponseDTO(
                companyEntity.getIdCompany(),
                companyEntity.getName(),
                companyEntity.getOwner(),
                companyEntity.getEmail(),
                companyEntity.getCnpj(),
                companyEntity.getDescription()
        );
    }
}
