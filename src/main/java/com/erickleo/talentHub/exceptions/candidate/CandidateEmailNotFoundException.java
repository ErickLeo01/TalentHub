package com.erickleo.talentHub.exceptions.candidate;

public class CandidateEmailNotFoundException extends RuntimeException {
    public CandidateEmailNotFoundException() {
        super("E-mail não encontrado!");
    }
}
