package com.erickleo.talenthub_api.modules.job.exception;

public class JobExistingIDException extends RuntimeException {
    public JobExistingIDException() {
        super("Vaga já existe.");
    }
}
