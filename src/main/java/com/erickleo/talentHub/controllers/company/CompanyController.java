package com.erickleo.talentHub.controllers.company;

import com.erickleo.talentHub.dtos.company.CompanyResponseDTO;
import com.erickleo.talentHub.dtos.company.CreateCompanyDTO;
import com.erickleo.talentHub.dtos.company.UpdateCompanyDTO;
import com.erickleo.talentHub.entities.CompanyEntity;
import com.erickleo.talentHub.useCases.company.CreateCompanyUseCase;
import com.erickleo.talentHub.useCases.company.DeleteCompanyUseCase;
import com.erickleo.talentHub.useCases.company.UpdateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/empresa")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @Autowired
    private UpdateCompanyUseCase updateCompanyUseCase;

    @Autowired
    private DeleteCompanyUseCase deleteCompanyUseCase;

    @PostMapping("/criarEmpresa")
    public CompanyResponseDTO createCompany(@Valid @RequestBody CreateCompanyDTO createCompanyDTO) {

        return createCompanyUseCase.execute(createCompanyDTO) ;
    }

    @PutMapping("/{id}")
    public CompanyResponseDTO updateCompany(@PathVariable("id") UUID idCompany,
                                            @Valid @RequestBody UpdateCompanyDTO updateCompanyDTO) {

        return updateCompanyUseCase.execute(idCompany, updateCompanyDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@Valid @PathVariable("id") UUID idCompany) {

        return deleteCompanyUseCase.execute(idCompany);
    }
}
