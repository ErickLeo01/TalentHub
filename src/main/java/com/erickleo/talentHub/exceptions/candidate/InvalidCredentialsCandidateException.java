package com.erickleo.talentHub.exceptions.candidate;

public class InvalidCredentialsCandidateException extends RuntimeException {
    public InvalidCredentialsCandidateException() {
        super("E-mail ou senha inválido!");
    }
}
