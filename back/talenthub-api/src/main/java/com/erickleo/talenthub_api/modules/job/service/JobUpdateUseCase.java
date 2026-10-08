package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.job.dto.JobDTO;
import com.erickleo.talenthub_api.modules.job.dto.JobResponseDTO;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotUpdateException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobUpdateUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobResponseDTO updateJob(UUID id, JobDTO jobDTO) {

        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(JobNotUpdateException::new);

        // Pega o UUID da empresa autenticada pelo JWT.
        String userId = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UUID companyId = UUID.fromString(userId);

        // Verifica se a vaga pertence à empresa autenticada.
        if (!jobEntity.getCompany().getId().equals(companyId)) {
            throw new JobNotUpdateException();
        }

        jobEntity.setName(jobDTO.name());
        jobEntity.setDescription(jobDTO.description());
        jobEntity.setBenefits(jobDTO.benefits());
        jobEntity.setSalary(jobDTO.salary());

        JobEntity updatedJob = jobRepository.save(jobEntity);

        return new JobResponseDTO(
                updatedJob.getName(),
                updatedJob.getDescription(),
                updatedJob.getSalary(),
                updatedJob.getBenefits()
        );
    }
}
