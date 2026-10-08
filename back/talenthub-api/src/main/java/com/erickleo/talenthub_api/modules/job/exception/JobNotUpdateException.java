package com.erickleo.talenthub_api.modules.job.exception;

public class JobNotUpdateException extends RuntimeException {
    public JobNotUpdateException() {
        super("Não é possível atualizar a vaga. Verifique se o ID existe e se você é o proprietário.");
    }
}
