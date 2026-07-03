package com.erickleo.talentHub.useCases.candidate;

import com.erickleo.talentHub.dtos.candidate.CandidateResponseDTO;
import com.erickleo.talentHub.dtos.candidate.UpdateCandidateDTO;
import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.exceptions.candidate.CandidateCPFAlreadyException;
import com.erickleo.talentHub.exceptions.candidate.CandidateEmailAlreadyException;
import com.erickleo.talentHub.exceptions.candidate.CandidateIdNotFoundException;
import com.erickleo.talentHub.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateResponseDTO execute(UUID idCandidate, UpdateCandidateDTO updateCandidateDTO) {

        CandidateEntity candidateEntity = candidateRepository.findById(idCandidate)
                .orElseThrow(CandidateIdNotFoundException::new);

        // VERIFICAR SE O CPF JÁ PERTENCE A OUTRO CANDIDATO
        Optional<CandidateEntity> candidateByCpf =
                candidateRepository.findByCpf(updateCandidateDTO.cpf());

        if (candidateByCpf.isPresent()
                && !candidateByCpf.get().getIdCandidate().equals(idCandidate)) {
            throw new CandidateCPFAlreadyException();
        }

        // VERIFICAR SE O E-MAIL JÁ PERTENCE A OUTRO CANDIDATO
        Optional<CandidateEntity> candidateByEmail =
                candidateRepository.findByEmail(updateCandidateDTO.email());

        if (candidateByEmail.isPresent()
                && !candidateByEmail.get().getIdCandidate().equals(idCandidate)) {
            throw new CandidateEmailAlreadyException();
        }

        candidateEntity.setName(updateCandidateDTO.name());
        candidateEntity.setEmail(updateCandidateDTO.email());
        candidateEntity.setPassword(updateCandidateDTO.password());
        candidateEntity.setCpf(updateCandidateDTO.cpf());
        candidateEntity.setCity(updateCandidateDTO.city());
        candidateEntity.setCurriculum(updateCandidateDTO.curriculum());

        CandidateEntity candidateUpdated = candidateRepository.save(candidateEntity);

        return new CandidateResponseDTO(
                candidateUpdated.getIdCandidate(),
                candidateUpdated.getName(),
                candidateUpdated.getEmail(),
                candidateUpdated.getCity(),
                candidateUpdated.getCurriculum()
        );
    }
}