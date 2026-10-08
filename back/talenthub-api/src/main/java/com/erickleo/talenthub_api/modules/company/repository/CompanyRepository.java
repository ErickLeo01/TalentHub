package com.erickleo.talenthub_api.modules.company.repository;

import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    boolean existsByEmail (String email);
    boolean existsByCnpj (String cnpj);
    boolean existsByEmailAndIdNot(String email, UUID id);
    boolean existsByCnpjAndIdNot(String cnpj, UUID id);
    Optional<CompanyEntity> findByEmail(String email);
    List<CompanyEntity> findByNameContainingIgnoreCase (String name);
}
