package com.erickleo.talentHub.useCases.candidate;

import com.erickleo.talentHub.dtos.candidate.CandidateResponseDTO;
import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.exceptions.candidate.CandidateIdNotFoundException;
import com.erickleo.talentHub.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShowCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateResponseDTO showCandidate(UUID idCandidate) {

        CandidateEntity candidateEntity = candidateRepository.findById(idCandidate)
                .orElseThrow(CandidateIdNotFoundException::new);

        return new CandidateResponseDTO(
                candidateEntity.getIdCandidate(),
                candidateEntity.getName(),
                candidateEntity.getEmail(),
                candidateEntity.getCity(),
                candidateEntity.getCurriculum()
        );
    }
}
