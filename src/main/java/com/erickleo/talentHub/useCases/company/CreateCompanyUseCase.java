package com.erickleo.talentHub.useCases.company;

import com.erickleo.talentHub.dtos.company.CompanyResponseDTO;
import com.erickleo.talentHub.dtos.company.CreateCompanyDTO;
import com.erickleo.talentHub.entities.CompanyEntity;
import com.erickleo.talentHub.exceptions.company.CompanyCNPJAlreadyException;
import com.erickleo.talentHub.exceptions.company.CompanyEmailAlreadyException;
import com.erickleo.talentHub.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyResponseDTO execute(CreateCompanyDTO createCompanyDTO) {

        // VALIDAÇÃO DO EMAIL.
        // SE O E-MAIL EXISTIR, DIGA QUE JÁ POSUUI UM E-MAIL IGUAL A ESSE.
        if(companyRepository.findByEmail(createCompanyDTO.email()).isPresent()) {
        throw  new CompanyEmailAlreadyException();
        }

        // VALIDAÇÃO DO CNPJ

        if(companyRepository.findByCnpj(createCompanyDTO.cnpj()).isPresent()) {
            throw new CompanyCNPJAlreadyException();
        }

        CompanyEntity companyEntity = new CompanyEntity();

        companyEntity.setName(createCompanyDTO.name());
        companyEntity.setOwner(createCompanyDTO.owner());
        companyEntity.setEmail(createCompanyDTO.email());
        companyEntity.setPassword(passwordEncoder.encode(createCompanyDTO.password()));
        companyEntity.setCnpj(createCompanyDTO.cnpj());
        companyEntity.setDescription(createCompanyDTO.description());

        CompanyEntity companySaved = companyRepository.save(companyEntity);

        return new CompanyResponseDTO(
                companySaved.getIdCompany(),
                companySaved.getName(),
                companySaved.getOwner(),
                companySaved.getEmail(),
                companySaved.getCnpj(),
                companySaved.getDescription()
        );

    }
}