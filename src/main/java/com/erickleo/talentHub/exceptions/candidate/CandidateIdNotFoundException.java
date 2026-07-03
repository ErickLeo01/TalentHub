package com.erickleo.talentHub.exceptions.candidate;

public class CandidateIdNotFoundException extends RuntimeException {
    public CandidateIdNotFoundException() {
        super("Id do candidato não encontrado!");
    }
}
