package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import com.erickleo.talenthub_api.modules.company.exception.CompanyNotFoundException;
import com.erickleo.talenthub_api.modules.company.repository.CompanyRepository;
import com.erickleo.talenthub_api.modules.job.dto.JobResponseDTO;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.erickleo.talenthub_api.modules.job.dto.JobDTO;
import java.util.UUID;

@Service
public class JobCreateUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobResponseDTO createJob(JobDTO jobDTO) {

        String userId = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UUID companyId = UUID.fromString(userId);

        CompanyEntity companyEntity = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        JobEntity jobEntity = new JobEntity();

        jobEntity.setName(jobDTO.name());
        jobEntity.setDescription(jobDTO.description());
        jobEntity.setSalary(jobDTO.salary());
        jobEntity.setBenefits(jobDTO.benefits());
        jobEntity.setCompany(companyEntity);

        JobEntity savedJob = jobRepository.save(jobEntity);

        return new JobResponseDTO(
                savedJob.getName(),
                savedJob.getDescription(),
                savedJob.getSalary(),
                savedJob.getBenefits()
        );
    }
}