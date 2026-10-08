package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotFoundException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyFindByNameUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyResponseDTO> showCompany(String enteredNameCompany) {

        List<CompanyEntity> companies =
                companyRepository.findByNameContainingIgnoreCase(enteredNameCompany);

        if (companies.isEmpty()) {
            throw new CompanyNotFoundException();
        }

        return companies.stream()
                .map(company -> new CompanyResponseDTO(
                        company.getName(),
                        company.getEmail(),
                        company.getDescription(),
                        company.getAddress()
                ))
                .toList();
    }
}