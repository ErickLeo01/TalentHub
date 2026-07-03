package com.erickleo.talentHub.exceptions.company;

public class CompanyEmailAlreadyException extends RuntimeException {
    public CompanyEmailAlreadyException() {
        super("E-mail já cadastrado!");
    }
}
