package com.erickleo.talenthub_api.modules.exception;

public class LoginException extends RuntimeException {

    public LoginException() {
        super("E-mail ou senha incorretos.");
    }
}