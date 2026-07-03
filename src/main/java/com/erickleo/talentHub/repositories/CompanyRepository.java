package com.erickleo.talentHub.repositories;

import com.erickleo.talentHub.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByEmail(String email);

    Optional<CompanyEntity> findByCnpj(String cnpj);
}
