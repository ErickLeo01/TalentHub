package com.erickleo.talentHub.useCases.company;

import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.entities.CompanyEntity;
import com.erickleo.talentHub.exceptions.company.CompanyIdNotFoundException;
import com.erickleo.talentHub.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public String execute(UUID idCompany) {

        CompanyEntity companyDeleted = companyRepository.findById(idCompany)
                .orElseThrow(CompanyIdNotFoundException::new);

        companyRepository.delete(companyDeleted);

        return "Empresa deletada com sucesso!";

    }
}
