package com.erickleo.talentHub.exceptions;

import com.erickleo.talentHub.exceptions.candidate.CandidateCPFAlreadyException;
import com.erickleo.talentHub.exceptions.candidate.CandidateEmailAlreadyException;
import com.erickleo.talentHub.exceptions.candidate.CandidateIdNotFoundException;
import com.erickleo.talentHub.exceptions.company.CompanyCNPJAlreadyException;
import com.erickleo.talentHub.exceptions.company.CompanyEmailAlreadyException;
import com.erickleo.talentHub.exceptions.company.CompanyIdNotFoundException;
import com.erickleo.talentHub.exceptions.job.JobIdNotFoundException;
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

    // -------CANDIDATO-------
    @ExceptionHandler(CandidateIdNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleCandidateIdNotFound(CandidateIdNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(
                e.getMessage(),
                "candidatoId"
        ));
    }

    @ExceptionHandler(CandidateCPFAlreadyException.class)
    public ResponseEntity<ErrorMessageDTO> handleCandidateCPFAlready(CandidateCPFAlreadyException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessageDTO(
                        e.getMessage(),
                        "candidatoCPF"
                ));
    }

    @ExceptionHandler(CandidateEmailAlreadyException.class)
    public ResponseEntity<ErrorMessageDTO> handleCandidateEmailAlready(CandidateEmailAlreadyException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessageDTO(
                        e.getMessage(),
                        "candidatoEmail"
                ));
    }

    // -------EMPRESA-------

    @ExceptionHandler(CompanyCNPJAlreadyException.class)
    public ResponseEntity<ErrorMessageDTO> handleCompanyCNPJAlread(CompanyCNPJAlreadyException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessageDTO(
                        e.getMessage(),
                        "empresaCNPJ"
                ));
    }

    @ExceptionHandler(CompanyEmailAlreadyException.class)
    public ResponseEntity<ErrorMessageDTO> handleCompanyEmailAlread(CompanyEmailAlreadyException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessageDTO(
                        e.getMessage(),
                        "empresaEmail"
                ));
    }

    @ExceptionHandler(CompanyIdNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleCompanyIdNotFound(CompanyIdNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageDTO(
                        e.getMessage(),
                        "empresaID"
                ));
    }

    // -------VAGA-------

    @ExceptionHandler(JobIdNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleJobIdNotFound(JobIdNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageDTO(
                        e.getMessage(),
                        "jobId"
                ));
    }

}
