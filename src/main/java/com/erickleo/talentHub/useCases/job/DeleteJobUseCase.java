package com.erickleo.talentHub.useCases.job;

import com.erickleo.talentHub.entities.JobEntity;
import com.erickleo.talentHub.exceptions.job.JobIdNotFoundException;
import com.erickleo.talentHub.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public String execute(UUID idJob) {

        JobEntity jobEntity = jobRepository.findById(idJob)
                .orElseThrow(JobIdNotFoundException::new);

        jobRepository.delete(jobEntity);

        return "Vaga deletada com sucesso!";
    }
}
