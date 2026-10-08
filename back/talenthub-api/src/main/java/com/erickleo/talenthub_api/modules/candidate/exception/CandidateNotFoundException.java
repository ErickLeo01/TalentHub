package com.erickleo.talenthub_api.modules.candidate.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException() {
        super("Nome do candidato não encontrado. Tente novamente.");
    }
}
