package com.erickleo.talenthub_api.modules.candidate.controller;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateLoginDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateLoginResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateUpdateDTO;
import com.erickleo.talenthub_api.modules.candidate.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
public class CandidateController {

    @Autowired
    private CandidateCreateUseCase candidateCreateUseCase;

    @Autowired
    private CandidateUpdateUseCase candidateUpdateUseCase;

    @Autowired
    private CandidateDeleteUseCase candidateDeleteUseCase;

    @Autowired
    private CandidateFindByNameUseCase candidateFindByNameUseCase;

    @Autowired
    private CandidateLoginUseCase candidateLoginUseCase;

    @Autowired
    private CandidateApplyJobUseCase candidateApplyJobUseCase;

    @Autowired
    private CandidateFindByIdUseCase candidateFindByIdUseCase;

    @PostMapping("/criar")
    public CandidateResponseDTO createCandidate(
            @Valid @RequestBody CandidateDTO candidateDTO) {
        return candidateCreateUseCase.createCandidate(candidateDTO);
    }

    @PutMapping("/atualizar/{id}")
    public CandidateResponseDTO updateCandidate(
            @PathVariable UUID id,
            @Valid @RequestBody CandidateUpdateDTO candidateUpdateDTO) {
        return candidateUpdateUseCase.updateCandidate(id, candidateUpdateDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public String deleteCandidate(@PathVariable UUID id) {
        return candidateDeleteUseCase.deleteCandidate(id);
    }

    @GetMapping("/buscar")
    public List<CandidateResponseDTO> searchName(
            @RequestParam(required = false, defaultValue = "") String name) {
        return candidateFindByNameUseCase.showCandidate(name);
    }

    @PostMapping("/login")
    public CandidateLoginResponseDTO login(
            @RequestBody CandidateLoginDTO candidateLoginDTO) {
        return candidateLoginUseCase.login(candidateLoginDTO);
    }

    @PostMapping("/candidatar/{jobId}")
    public String applyJob(@PathVariable UUID jobId) {
        return candidateApplyJobUseCase.applyJob(jobId);
    }

    @GetMapping("/buscarporid")
    public CandidateResponseDTO searchId(@RequestParam UUID id) {
        return candidateFindByIdUseCase.findCandidateById(id);
    }

    @GetMapping("/{id}")
    public CandidateResponseDTO findById(@PathVariable UUID id) {
        return candidateFindByIdUseCase.findCandidateById(id);
    }

    // Busca o perfil do candidato autenticado pelo JWT.
    @GetMapping("/me")
    public CandidateResponseDTO findMyProfile() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_CANDIDATE".equals(authority.getAuthority()) ||
                                "CANDIDATE".equals(authority.getAuthority()))) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Acesso permitido somente a candidatos autenticados."
            );
        }

        UUID candidateId;

        try {
            candidateId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Identificação do candidato inválida."
            );
        }

        return candidateFindByIdUseCase.findCandidateById(candidateId);
    }
}