package com.erickleo.talenthub_api.modules.exception;

import com.erickleo.talenthub_api.modules.candidate.exception.CandidateExistingEmailORCPFException;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotUpdateException;
import com.erickleo.talenthub_api.modules.company.exception.CompanyExistingEmailORCNPJException;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotFoundException;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotUpdateException;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.exception.JobNotUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            CandidateNotFoundException.class,
            CompanyNotFoundException.class,
            JobNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({
            CandidateExistingEmailORCPFException.class,
            CompanyExistingEmailORCNPJException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflictException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler({
            CandidateNotUpdateException.class,
            CompanyNotUpdateException.class,
            JobNotUpdateException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleNotUpdateException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleLoginException(LoginException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
    }
}