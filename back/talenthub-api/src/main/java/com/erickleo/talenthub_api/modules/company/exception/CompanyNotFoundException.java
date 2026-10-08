package com.erickleo.talenthub_api.modules.company.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Nome da empresa não encontrado. Tente novamente.");
    }
}
