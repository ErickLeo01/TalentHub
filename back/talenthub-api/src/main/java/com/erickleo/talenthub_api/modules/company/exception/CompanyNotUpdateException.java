package com.erickleo.talenthub_api.modules.company.exception;

public class CompanyNotUpdateException extends RuntimeException {
    public CompanyNotUpdateException() {
        super("Não é possível atualizar a empresa. Verifique se o ID existe e se você é o proprietário.");
        ;
    }
}
