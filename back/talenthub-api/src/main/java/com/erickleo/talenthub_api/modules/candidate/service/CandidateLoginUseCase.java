package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.dto.CandidateLoginDTO;
import com.erickleo.talenthub_api.modules.candidate.dto.CandidateLoginResponseDTO;
import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import com.erickleo.talenthub_api.modules.exception.LoginException;
import com.erickleo.talenthub_api.modules.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateLoginUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public CandidateLoginResponseDTO login(CandidateLoginDTO candidateLoginDTO) {

        CandidateEntity candidateEntity = candidateRepository
                .findByEmail(candidateLoginDTO.username())
                .orElseThrow(LoginException::new);

        boolean passwordMatches = passwordEncoder.matches(
                candidateLoginDTO.password(),
                candidateEntity.getPassword()
        );

        if (!passwordMatches) {
            throw new LoginException();
        }

        String token = jwtProvider.generateToken(
                candidateEntity.getId(),
                "CANDIDATE"
        );

        return new CandidateLoginResponseDTO(token);
    }
}