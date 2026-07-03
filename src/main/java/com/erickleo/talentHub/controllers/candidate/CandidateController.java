package com.erickleo.talentHub.controllers.candidate;

import com.erickleo.talentHub.dtos.candidate.CandidateResponseDTO;
import com.erickleo.talentHub.dtos.candidate.CreateCandidateDTO;
import com.erickleo.talentHub.dtos.candidate.UpdateCandidateDTO;
import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.useCases.candidate.CreateCandidateUseCase;
import com.erickleo.talentHub.useCases.candidate.DeleteCandidateUseCase;
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
}
