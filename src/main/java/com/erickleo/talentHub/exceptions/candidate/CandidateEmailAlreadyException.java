package com.erickleo.talentHub.exceptions.candidate;

public class CandidateEmailAlreadyException extends RuntimeException {
    public CandidateEmailAlreadyException() {
        super("E-mail do candidato já existente!");
    }
}
