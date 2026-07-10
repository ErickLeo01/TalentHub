package com.erickleo.talentHub.controllers.candidate;

import com.erickleo.talentHub.dtos.candidate.*;
import com.erickleo.talentHub.dtos.company.LoginCompanyDTO;
import com.erickleo.talentHub.dtos.company.LoginCompanyResponseDTO;
import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.useCases.candidate.CreateCandidateUseCase;
import com.erickleo.talentHub.useCases.candidate.DeleteCandidateUseCase;
import com.erickleo.talentHub.useCases.candidate.LoginCandidateUseCase;
import com.erickleo.talentHub.useCases.candidate.UpdateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase  createCandidateUseCase;

    @Autowired
    private UpdateCandidateUseCase   updateCandidateUseCase;

    @Autowired
    private DeleteCandidateUseCase deleteCandidateUseCase;

    @Autowired
    private LoginCandidateUseCase loginCandidateUseCase;

    @PostMapping("/criarCandidato")
    public CandidateResponseDTO createCandidate(@Valid @RequestBody CreateCandidateDTO createCandidateDTO) {

        return createCandidateUseCase.execute(createCandidateDTO);
    }

    @PutMapping("/{id}")
    public CandidateResponseDTO updateCandidate(@PathVariable("id") UUID idCandidate,
            @Valid @RequestBody UpdateCandidateDTO updateCandidateDTO) {

        return updateCandidateUseCase.execute(idCandidate, updateCandidateDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteCandidate(@PathVariable("id") UUID idCandidate) {

        return deleteCandidateUseCase.execute(idCandidate);
    }

    @PostMapping("/login")
    public LoginCandidateResponseDTO login(
            @Valid @RequestBody LoginCandidateDTO loginCandidateDTO) {

        return loginCandidateUseCase.execute(loginCandidateDTO);
    }
}