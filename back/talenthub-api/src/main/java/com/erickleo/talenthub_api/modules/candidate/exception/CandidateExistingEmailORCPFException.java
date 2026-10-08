package com.erickleo.talenthub_api.modules.candidate.exception;

public class CandidateExistingEmailORCPFException extends RuntimeException {
    public CandidateExistingEmailORCPFException() {
        super("Já existe um candidato com esse e-mail ou CPF.");
    }
}
