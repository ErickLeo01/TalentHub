package com.erickleo.talenthub_api.modules.candidate.repository;

import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
    boolean existsByCpfAndIdNot(String cpf, UUID id);
    Optional<CandidateEntity> findByEmail(String email);
    List<CandidateEntity> findByNameContainingIgnoreCase(String name);
}
