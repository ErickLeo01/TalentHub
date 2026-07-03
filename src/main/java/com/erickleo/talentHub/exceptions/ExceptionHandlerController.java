package com.erickleo.talentHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> error(MethodArgumentNotValidException e) {

        List<ErrorMessageDTO> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new ErrorMessageDTO(
                    error.getDefaultMessage(),
                    error.getField()
            ));
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
