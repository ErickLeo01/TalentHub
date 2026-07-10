package com.erickleo.talentHub.useCases.company;

import com.erickleo.talentHub.dtos.company.LoginCompanyDTO;
import com.erickleo.talentHub.dtos.company.LoginCompanyResponseDTO;
import com.erickleo.talentHub.entities.CompanyEntity;
import com.erickleo.talentHub.exceptions.company.CompanyEmailNotFoundException;
import com.erickleo.talentHub.exceptions.company.InvalidCredentialsCompanyException;
import com.erickleo.talentHub.repositories.CompanyRepository;
import com.erickleo.talentHub.security.JWTProvider;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider  jwtProvider;

    public LoginCompanyResponseDTO execute(LoginCompanyDTO loginCompanyDTO) {

        CompanyEntity companyEntity = companyRepository.findByEmail(loginCompanyDTO.email())
                .orElseThrow(CompanyEmailNotFoundException::new);


        // variável guarda o resultado da comparação da senha.
        var passwordMatches = passwordEncoder.matches(loginCompanyDTO.password(),
                companyEntity.getPassword());

        if(!passwordMatches) {

            throw new InvalidCredentialsCompanyException();
        }

        return new LoginCompanyResponseDTO(
                jwtProvider.generateToken(companyEntity.getIdCompany())
        );
    }
}
