package com.erickleo.talenthub_api.modules.company.controller;

import com.erickleo.talenthub_api.modules.company.dto.CompanyDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyLoginDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyLoginResponseDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

    @Autowired
    private CompanyCreateUseCase companyCreateUseCase;

    @Autowired
    private CompanyUpdateUseCase companyUpdateUseCase;

    @Autowired
    private CompanyDeleteUseCase companyDeleteUseCase;

    @Autowired
    private CompanyFindByNameUseCase companyFindByNameUseCase;

    @Autowired
    private CompanyLoginUseCase companyLoginUseCase;

    @PostMapping("/criar")
    public CompanyResponseDTO createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        return companyCreateUseCase.createCompany(companyDTO);
    }

    @PutMapping("/atualizar/{id}")
    public CompanyResponseDTO updateCompany(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyDTO companyDTO) {

        return companyUpdateUseCase.updateCompany(id, companyDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public String deleteCompany(@PathVariable UUID id) {
        return companyDeleteUseCase.deleteCompany(id);
    }

    @GetMapping("/buscar")
    public List<CompanyResponseDTO> searchCompany(@RequestParam String name) {
        return companyFindByNameUseCase.showCompany(name);
    }

    @PostMapping("/login")
    public CompanyLoginResponseDTO login(@RequestBody CompanyLoginDTO companyLoginDTO) {
        return companyLoginUseCase.login(companyLoginDTO);
    }
}