package com.erickleo.talentHub.repositories;

import com.erickleo.talentHub.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByEmail(String email);

    Optional<CandidateEntity> findByCpf(String cpf);

    List<CandidateEntity> findByCity(String city);
}
