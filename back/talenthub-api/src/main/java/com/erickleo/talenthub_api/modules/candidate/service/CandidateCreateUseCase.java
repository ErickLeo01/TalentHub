package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateExistingEmailORCPFException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateCreateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateResponseDTO createCandidate(@Valid CandidateDTO candidateDTO) {

        CandidateEntity candidateEntity = new CandidateEntity();

        candidateEntity.setName(candidateDTO.name());
        candidateEntity.setEmail(candidateDTO.email());
        candidateEntity.setPassword(passwordEncoder.encode(candidateDTO.password()));
        candidateEntity.setDescription(candidateDTO.description());
        candidateEntity.setCpf(candidateDTO.cpf());

        if (candidateRepository.existsByEmail(candidateEntity.getEmail()) ||
                candidateRepository.existsByCpf(candidateEntity.getCpf())) {

            throw new CandidateExistingEmailORCPFException();
        }

        CandidateEntity savedCandidate = candidateRepository.save(candidateEntity);

        return new CandidateResponseDTO(
                savedCandidate.getName(),
                savedCandidate.getEmail(),
                savedCandidate.getDescription()
        );
    }
}