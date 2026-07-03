package com.erickleo.talentHub.exceptions.company;

public class CompanyCNPJAlreadyException extends RuntimeException {
    public CompanyCNPJAlreadyException() {
        super("CNPJ já cadastrado!");
    }
}
