package com.erickleo.talentHub.useCases.job;

import com.erickleo.talentHub.dtos.job.JobResponseDTO;
import com.erickleo.talentHub.entities.JobEntity;
import com.erickleo.talentHub.exceptions.job.JobIdNotFoundException;
import com.erickleo.talentHub.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShowJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobResponseDTO showJob(UUID idJob) {

        JobEntity jobEntity = jobRepository.findById(idJob)
                .orElseThrow(JobIdNotFoundException::new);

        return new JobResponseDTO(
          jobEntity.getIdJob(),
          jobEntity.getTitle(),
          jobEntity.getLevel(),
          jobEntity.getWage(),
          jobEntity.getDescription()
        );
    }
}
