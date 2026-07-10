package com.erickleo.talentHub.useCases.company;

import com.auth0.jwt.algorithms.Algorithm;
import com.erickleo.talentHub.repositories.CompanyRepository;
import com.erickleo.talentHub.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JWTProvider jwtProvider;


}
