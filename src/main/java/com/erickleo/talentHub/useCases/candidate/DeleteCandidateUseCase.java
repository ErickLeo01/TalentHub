package com.erickleo.talentHub.useCases.candidate;

import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.exceptions.candidate.CandidateIdNotFoundException;
import com.erickleo.talentHub.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public String execute (UUID idCandidate) {

        CandidateEntity candidateExclued = candidateRepository.findById(idCandidate).orElseThrow(CandidateIdNotFoundException::new);

        candidateRepository.delete(candidateExclued);

        return "Candidato excluído com sucesso!";
    }
}
