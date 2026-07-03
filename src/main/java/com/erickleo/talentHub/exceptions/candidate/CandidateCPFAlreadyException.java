package com.erickleo.talentHub.exceptions.candidate;

public class CandidateCPFAlreadyException extends RuntimeException {
    public CandidateCPFAlreadyException() {
        super("Já existe um candidado com esse CPF!");
    }
}
