package com.erickleo.talentHub.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

public record ErrorMessageDTO (String message, String field) {


}
