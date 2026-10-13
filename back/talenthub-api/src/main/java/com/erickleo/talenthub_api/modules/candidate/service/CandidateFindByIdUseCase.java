
package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotUpdateException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidateFindByIdUseCase {

    private final CandidateRepository candidateRepository;

    public CandidateFindByIdUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateResponseDTO findCandidateById(UUID id) {

        if (id == null) {
            throw new CandidateNotFoundException();
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
            throw new CandidateNotUpdateException();
        }

        CandidateEntity candidateEntity = candidateRepository.findById(id)
                .orElseThrow(CandidateNotFoundException::new);

        return new CandidateResponseDTO(
                candidateEntity.getId(),
                candidateEntity.getName(),
                candidateEntity.getEmail(),
                candidateEntity.getDescription()
        );
    }
}
