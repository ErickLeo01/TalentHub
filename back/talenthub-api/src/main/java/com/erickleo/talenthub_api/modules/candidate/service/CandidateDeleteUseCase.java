package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotUpdateException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CandidateDeleteUseCase {

    private final CandidateRepository candidateRepository;

    public CandidateDeleteUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public String deleteCandidate(UUID idCandidate) {
        if (idCandidate == null) {
            throw new CandidateNotFoundException();
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

        if (!authenticatedCandidateId.equals(idCandidate)) {
            throw new CandidateNotUpdateException();
        }

        CandidateEntity candidateEntity = candidateRepository.findById(idCandidate)
                .orElseThrow(CandidateNotFoundException::new);

        candidateRepository.delete(candidateEntity);
        return "Candidato deletado com sucesso.";
    }
}
