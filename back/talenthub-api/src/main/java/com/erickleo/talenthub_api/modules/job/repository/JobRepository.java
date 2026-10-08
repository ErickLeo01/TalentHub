package com.erickleo.talenthub_api.modules.job.repository;

import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    public List<JobEntity> findByNameContainingIgnoreCase (String name);
    public JobEntity findBySalary(double salary);
}
