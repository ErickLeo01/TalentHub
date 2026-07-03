package com.erickleo.talentHub.useCases.job;

import com.erickleo.talentHub.dtos.job.CreateJobDTO;
import com.erickleo.talentHub.dtos.job.JobResponseDTO;
import com.erickleo.talentHub.entities.JobEntity;
import com.erickleo.talentHub.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobResponseDTO execute(CreateJobDTO createJobDTO) {

        JobEntity jobEntity = new JobEntity();

        jobEntity.setTitle(createJobDTO.title());
        jobEntity.setLevel(createJobDTO.level());
        jobEntity.setWage(createJobDTO.wage());
        jobEntity.setDescription(createJobDTO.description());


        JobEntity jobSaved = jobRepository.save(jobEntity);

        return new JobResponseDTO(
                jobSaved.getIdJob(),
                jobSaved.getTitle(),
                jobSaved.getLevel(),
                jobSaved.getWage(),
                jobSaved.getDescription()
        );
    }
    }