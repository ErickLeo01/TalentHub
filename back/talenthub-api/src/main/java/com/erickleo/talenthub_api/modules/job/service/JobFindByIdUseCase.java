package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.job.dto.JobResponseDTO;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class JobFindByIdUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobResponseDTO findJobById(UUID id) {

        if (id == null) {
            throw new JobNotFoundException();
        }

        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(JobNotFoundException::new);

        return new JobResponseDTO(
                jobEntity.getId(),
                jobEntity.getName(),
                jobEntity.getDescription(),
                jobEntity.getSalary(),
                jobEntity.getBenefits()
        );
    }
}
