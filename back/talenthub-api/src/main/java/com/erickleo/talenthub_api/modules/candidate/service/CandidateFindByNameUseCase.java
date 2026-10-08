package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateFindByNameUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<CandidateResponseDTO> showCandidate(String enteredNameCandidate) {

        List<CandidateEntity> candidates =
                candidateRepository.findByNameContainingIgnoreCase(enteredNameCandidate);

        if (candidates.isEmpty()) {
            throw new CandidateNotFoundException();
        }

        return candidates.stream()
                .map(candidate -> new CandidateResponseDTO(
                        candidate.getName(),
                        candidate.getEmail(),
                        candidate.getDescription()
                ))
                .toList();
    }
}