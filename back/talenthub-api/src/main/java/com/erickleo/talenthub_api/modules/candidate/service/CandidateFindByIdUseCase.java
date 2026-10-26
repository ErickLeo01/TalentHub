package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CandidateFindByIdUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateResponseDTO findCandidateById(UUID id) {

        if (id == null) {
            throw new CandidateNotFoundException();
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
