package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateUpdateDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateExistingEmailORCPFException;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotUpdateException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@Service
public class CandidateUpdateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateResponseDTO updateCandidate(UUID id, CandidateUpdateDTO candidateDTO) {

        if (id == null || candidateDTO == null) {
            throw new CandidateNotUpdateException();
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_CANDIDATE".equals(authority.getAuthority()))) {
            throw new CandidateNotUpdateException();
        }

        final UUID authenticatedCandidateId;

        try {
            authenticatedCandidateId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new CandidateNotUpdateException();
        }

        if (!authenticatedCandidateId.equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não pode atualizar o cadastro de outro candidato."
            );
        }

        CandidateEntity candidateEntity = candidateRepository.findById(id)
                .orElseThrow(CandidateNotUpdateException::new);

        if (candidateDTO.name() != null
                && !candidateDTO.name().isBlank()) {
            candidateEntity.setName(candidateDTO.name());
        }

        if (candidateDTO.email() != null
                && !candidateDTO.email().isBlank()) {

            if (candidateRepository.existsByEmailAndIdNot(candidateDTO.email(), id)) {
                throw new CandidateExistingEmailORCPFException();
            }

            candidateEntity.setEmail(candidateDTO.email());
        }

        if (candidateDTO.password() != null
                && !candidateDTO.password().isBlank()) {
            candidateEntity.setPassword(
                    passwordEncoder.encode(candidateDTO.password())
            );
        }

        if (candidateDTO.cpf() != null
                && !candidateDTO.cpf().isBlank()) {

            if (candidateRepository.existsByCpfAndIdNot(candidateDTO.cpf(), id)) {
                throw new CandidateExistingEmailORCPFException();
            }

            candidateEntity.setCpf(candidateDTO.cpf());
        }

        if (candidateDTO.description() != null) {
            candidateEntity.setDescription(candidateDTO.description());
        }

        CandidateEntity updatedCandidate = candidateRepository.save(candidateEntity);

        return new CandidateResponseDTO(
                updatedCandidate.getId(),
                updatedCandidate.getName(),
                updatedCandidate.getEmail(),
                updatedCandidate.getDescription()
        );
    }
}
