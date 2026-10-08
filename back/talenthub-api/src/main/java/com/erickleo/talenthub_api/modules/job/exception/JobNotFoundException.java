package com.erickleo.talenthub_api.modules.job.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Nome da vaga não encontrado. Tente novamente.");
    }
}
