package com.erickleo.talentHub.exceptions.job;

public class JobIdNotFoundException extends RuntimeException {
    public JobIdNotFoundException() {
        super("ID da vaga não encontrado!");
    }
}
