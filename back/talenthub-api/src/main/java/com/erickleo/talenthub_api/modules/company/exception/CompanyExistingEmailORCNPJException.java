package com.erickleo.talenthub_api.modules.company.exception;

public class CompanyExistingEmailORCNPJException extends RuntimeException {
    public CompanyExistingEmailORCNPJException() {
        super("Já existe uma empresa cadastrada com esse e-mail ou CNPJ. Tente novamente com um e-mail ou CNPJ diferentes.");
    }
}
