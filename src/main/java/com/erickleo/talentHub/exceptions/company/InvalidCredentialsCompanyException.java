package com.erickleo.talentHub.exceptions.company;

public class InvalidCredentialsCompanyException extends RuntimeException {
    public InvalidCredentialsCompanyException() {
        super("E-mail ou senha inválido!");
    }
}
