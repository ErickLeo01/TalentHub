package com.erickleo.talenthub_api.modules.company.service;

import com.erickleo.talenthub_api.modules.company.dto.CompanyDTO;
import com.erickleo.talenthub_api.modules.company.dto.CompanyResponseDTO;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyExistingEmailORCNPJException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyCreateUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyResponseDTO createCompany(@Valid CompanyDTO companyDTO) {

        // Verifica se o e-mail ou CNPJ já estão cadastrados.
        if (companyRepository.existsByEmail(companyDTO.email())
                || companyRepository.existsByCnpj(companyDTO.cnpj())) {
            throw new CompanyExistingEmailORCNPJException();
        }

        // Cria a entidade da empresa.
        CompanyEntity companyEntity = new CompanyEntity();

        companyEntity.setName(companyDTO.name());
        companyEntity.setEmail(companyDTO.email());

        // Armazena a senha criptografada.
        companyEntity.setPassword(
                passwordEncoder.encode(companyDTO.password())
        );

        companyEntity.setCnpj(companyDTO.cnpj());
        companyEntity.setDescription(companyDTO.description());
        companyEntity.setAddress(companyDTO.address());

        // Salva a empresa no banco de dados.
        CompanyEntity savedCompany =
                companyRepository.save(companyEntity);

        // Retorna os dados sem expor a senha.
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getEmail(),
                savedCompany.getDescription(),
                savedCompany.getAddress(),
                savedCompany.getCnpj()
        );
    }
}
