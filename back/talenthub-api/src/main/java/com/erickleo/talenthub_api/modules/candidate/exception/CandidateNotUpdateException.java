package com.erickleo.talenthub_api.modules.candidate.exception;

public class CandidateNotUpdateException extends RuntimeException {
    public CandidateNotUpdateException() {
        super("Não é possível atualizar o candidato. Verifique se o ID existe e se você é o proprietário.");
    }
}
