package com.erickleo.talentHub.exceptions.company;

public class CompanyIdNotFoundException extends RuntimeException {
    public CompanyIdNotFoundException() {
        super("ID não encontrado!");
    }
}
