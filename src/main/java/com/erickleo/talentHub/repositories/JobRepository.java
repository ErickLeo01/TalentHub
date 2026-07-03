package com.erickleo.talentHub.repositories;

import com.erickleo.talentHub.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByTitle(String title);

    List<JobEntity> findByLevel(String level);

    List<JobEntity> findByWage(Double wage);
}
