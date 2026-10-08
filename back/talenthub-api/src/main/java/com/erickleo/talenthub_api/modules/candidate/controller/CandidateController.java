package com.erickleo.talenthub_api.modules.candidate.controller;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateLoginDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateLoginResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

    @Autowired
    private CandidateCreateUseCase candidateCreateUseCase;

    @Autowired
    private CandidateUpdateUseCase  candidateUpdateUseCase;

    @Autowired
    private CandidateDeleteUseCase candidateDeleteUseCase;

    @Autowired
    private CandidateFindByNameUseCase  candidateFindByNameUseCase;

    @Autowired
    private CandidateLoginUseCase candidateLoginUseCase;

    @Autowired
    private CandidateApplyJobUseCase candidateApplyJobUseCase;

    @PostMapping("/criar")
    public CandidateResponseDTO createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        return candidateCreateUseCase.createCandidate(candidateDTO);
    }

    @PutMapping("/atualizar/{id}")
    public CandidateResponseDTO updateCandidate(@PathVariable UUID id,
                   @Valid @RequestBody CandidateDTO candidateDTO) {
        return candidateUpdateUseCase.updateCandidate(id, candidateDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public String deleteCandidate(@PathVariable UUID id) {
        return candidateDeleteUseCase.deleteCandidate(id);
    }

    @GetMapping("/buscar")
    public List<CandidateResponseDTO> searchName(@RequestParam String name) {
        return candidateFindByNameUseCase.showCandidate(name);
    }

    @PostMapping("/login")
    public CandidateLoginResponseDTO login(@RequestBody CandidateLoginDTO candidateLoginDTO) {
        return candidateLoginUseCase.login(candidateLoginDTO);
    }

    @PostMapping("/candidatar/{jobId}")
    public String applyJob(@PathVariable UUID jobId) {
        return candidateApplyJobUseCase.applyJob(jobId);
    }
}
