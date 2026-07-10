package com.erickleo.talentHub.exceptions.company;

public class CompanyEmailNotFoundException extends RuntimeException {
    public CompanyEmailNotFoundException() {
        super("E-mail não encontrado!");
    }
}
