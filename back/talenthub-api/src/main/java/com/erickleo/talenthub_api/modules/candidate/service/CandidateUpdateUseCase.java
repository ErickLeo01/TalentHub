package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateExistingEmailORCPFException;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotUpdateException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidateUpdateUseCase {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CandidateUpdateUseCase(
            CandidateRepository candidateRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateResponseDTO updateCandidate(UUID id, CandidateDTO candidateDTO) {
        if (id == null || candidateDTO == null) {
            throw new CandidateNotUpdateException();
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority -> "ROLE_CANDIDATE".equals(authority.getAuthority()))) {
            throw new CandidateNotUpdateException();
        }

        final UUID authenticatedCandidateId;
        try {
            authenticatedCandidateId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new CandidateNotUpdateException();
        }

        if (!authenticatedCandidateId.equals(id)) {
            throw new CandidateNotUpdateException();
        }

        CandidateEntity candidateEntity = candidateRepository.findById(id)
                .orElseThrow(CandidateNotUpdateException::new);

        if (candidateRepository.existsByEmailAndIdNot(candidateDTO.email(), id)
                || candidateRepository.existsByCpfAndIdNot(candidateDTO.cpf(), id)) {
            throw new CandidateExistingEmailORCPFException();
        }

        candidateEntity.setName(candidateDTO.name());
        candidateEntity.setEmail(candidateDTO.email());
        candidateEntity.setPassword(passwordEncoder.encode(candidateDTO.password()));
        candidateEntity.setCpf(candidateDTO.cpf());
        candidateEntity.setDescription(candidateDTO.description());

        CandidateEntity updatedCandidate = candidateRepository.save(candidateEntity);

        return new CandidateResponseDTO(
                updatedCandidate.getName(),
                updatedCandidate.getEmail(),
                updatedCandidate.getDescription()
        );
    }
}
