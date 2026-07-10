package com.erickleo.talentHub.useCases.candidate;

import com.erickleo.talentHub.dtos.candidate.LoginCandidateDTO;
import com.erickleo.talentHub.dtos.candidate.LoginCandidateResponseDTO;
import com.erickleo.talentHub.entities.CandidateEntity;
import com.erickleo.talentHub.exceptions.candidate.CandidateEmailNotFoundException;
import com.erickleo.talentHub.exceptions.candidate.InvalidCredentialsCandidateException;
import com.erickleo.talentHub.exceptions.company.InvalidCredentialsCompanyException;
import com.erickleo.talentHub.repositories.CandidateRepository;
import com.erickleo.talentHub.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public LoginCandidateResponseDTO execute(LoginCandidateDTO loginCandidateDTO) {

        CandidateEntity candidateEntity = candidateRepository.findByEmail(loginCandidateDTO.email())
                .orElseThrow(CandidateEmailNotFoundException::new);

        // variável guarda o resultado da comparação da senha.
        var passwordMatches = passwordEncoder.matches(loginCandidateDTO.password(),
                candidateEntity.getPassword());

        if (!passwordMatches) {

            throw new InvalidCredentialsCandidateException();
        }

        return new LoginCandidateResponseDTO(
                jwtProvider.generateToken(candidateEntity.getIdCandidate())
        );
    }
}
