package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.job.dto.JobDTO;
import com.erickleo.talenthub_api.modules.job.dto.JobResponseDTO;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotUpdateException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class JobUpdateUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Transactional
    public JobResponseDTO updateJob(UUID id, JobDTO jobDTO) {

        if (id == null || jobDTO == null) {
            throw new JobNotUpdateException();
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_COMPANY".equals(authority.getAuthority()))) {
            throw new JobNotUpdateException();
        }

        final UUID companyId;

        try {
            companyId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new JobNotUpdateException();
        }

        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(JobNotUpdateException::new);

        if (jobEntity.getCompany() == null
                || !jobEntity.getCompany().getId().equals(companyId)) {
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