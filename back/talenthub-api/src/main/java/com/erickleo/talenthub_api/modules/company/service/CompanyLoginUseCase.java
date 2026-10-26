package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyLoginDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyLoginResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import com.erickleo.talenthub_api.modules.exception.LoginException;
import com.erickleo.talenthub_api.modules.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyLoginUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public CompanyLoginResponseDTO login(
            CompanyLoginDTO companyLoginDTO
    ) {

        // Verifica se os dados foram enviados.
        if (companyLoginDTO == null
                || companyLoginDTO.email() == null
                || companyLoginDTO.password() == null
                || companyLoginDTO.email().isBlank()
                || companyLoginDTO.password().isBlank()) {

            throw new LoginException();
        }

        // Busca a empresa pelo e-mail.
        CompanyEntity companyEntity = companyRepository
                .findByEmail(companyLoginDTO.email())
                .orElseThrow(LoginException::new);

        // Compara a senha informada com a senha criptografada.
        boolean passwordMatches = passwordEncoder.matches(
                companyLoginDTO.password(),
                companyEntity.getPassword()
        );

        if (!passwordMatches) {
            throw new LoginException();
        }

        // Gera o JWT para a empresa autenticada.
        String token = jwtProvider.generateToken(
                companyEntity.getId(),
                "COMPANY"
        );

        // Retorna o token ao cliente.
        return new CompanyLoginResponseDTO(token);
    }
}
