package com.erickleo.talentHub.useCases.company;

import com.erickleo.talentHub.dtos.candidate.CandidateResponseDTO;
import com.erickleo.talentHub.dtos.company.CompanyResponseDTO;
import com.erickleo.talentHub.dtos.company.UpdateCompanyDTO;
import com.erickleo.talentHub.entities.CompanyEntity;
import com.erickleo.talentHub.exceptions.company.CompanyIdNotFoundException;
import com.erickleo.talentHub.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UpdateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyResponseDTO execute(UUID idCompany, UpdateCompanyDTO updateCompanyDTO) {


            CompanyEntity companyEntity = companyRepository.findById(idCompany)
                    .orElseThrow(CompanyIdNotFoundException::new);

            companyEntity.setName(updateCompanyDTO.name());
            companyEntity.setOwner(updateCompanyDTO.owner());
            companyEntity.setEmail(updateCompanyDTO.email());
            companyEntity.setPassword(updateCompanyDTO.password());
            companyEntity.setCnpj(updateCompanyDTO.cnpj());
            companyEntity.setDescription(updateCompanyDTO.description());

            CompanyEntity companyUpdate = companyRepository.save(companyEntity);

            return new CompanyResponseDTO(
                    companyUpdate.getIdCompany(),
                    companyUpdate.getName(),
                    companyUpdate.getOwner(),
                    companyUpdate.getEmail(),
                    companyUpdate.getCnpj(),
                    companyUpdate.getDescription()
            );
    }
}
