package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.job.dto.JobResponseDTO;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobFindByNameUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobResponseDTO> showJob(String enteredNameJob) {

        List<JobEntity> jobs =
                jobRepository.findByNameContainingIgnoreCase(enteredNameJob);

        if (jobs.isEmpty()) {
            throw new JobNotFoundException();
        }

        return jobs.stream()
                .map(job -> new JobResponseDTO(
                        job.getName(),
                        job.getDescription(),
                        job.getSalary(),
                        job.getBenefits()
                ))
                .toList();
    }

}