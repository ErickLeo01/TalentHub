package com.erickleo.talentHub.useCases.candidate;

import com.erickleo.talentHub.dtos.candidate.CandidateResponseDTO;
import com.erickleo.talentHub.dtos.candidate.CreateCandidateDTO;
import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.exceptions.candidate.CandidateCPFAlreadyException;
import com.erickleo.talentHub.exceptions.candidate.CandidateEmailAlreadyException;
import com.erickleo.talentHub.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateResponseDTO execute (CreateCandidateDTO createCandidateDTO) {

        // VERIFICAR SE E-MAIL JÁ EXISTE.
        if(candidateRepository.findByEmail(createCandidateDTO.email()).isPresent()) {

            throw new CandidateEmailAlreadyException();
        }

        // VERIFICAR SE CPF JÁ EXISTE.
        if(candidateRepository.findByCpf(createCandidateDTO.cpf()).isPresent()) {
            throw new CandidateCPFAlreadyException();
        }

        CandidateEntity candidateEntity = new CandidateEntity();

        candidateEntity.setName(createCandidateDTO.name());
        candidateEntity.setEmail(createCandidateDTO.email());
        candidateEntity.setPassword(createCandidateDTO.password());
        candidateEntity.setPassword(passwordEncoder.encode(createCandidateDTO.password()));
        candidateEntity.setCity(createCandidateDTO.city());
        candidateEntity.setCurriculum(createCandidateDTO.curriculum());

        CandidateEntity candidateSaved = candidateRepository.save(candidateEntity);

        return new CandidateResponseDTO(
                candidateSaved.getIdCandidate(),
                candidateSaved.getName(),
                candidateSaved.getEmail(),
                candidateSaved.getCity(),
                candidateSaved.getCurriculum()
        );

    }
}
