package com.erickleo.talentHub.useCases.job;

import com.erickleo.talentHub.dtos.job.JobResponseDTO;
import com.erickleo.talentHub.dtos.job.UpdateJobDTO;
import com.erickleo.talentHub.entities.JobEntity;
import com.erickleo.talentHub.exceptions.job.JobIdNotFoundException;
import com.erickleo.talentHub.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobResponseDTO execute(UUID idJob, UpdateJobDTO updateJobDTO) {

        // Busca a vaga pelo ID informado
        JobEntity jobEntity = jobRepository.findById(idJob)
                .orElseThrow(JobIdNotFoundException::new);

        // Atualiza apenas os campos enviados
        if (updateJobDTO.title() != null) {
            jobEntity.setTitle(updateJobDTO.title());
        }

        if (updateJobDTO.level() != null) {
            jobEntity.setLevel(updateJobDTO.level());
        }

        if (updateJobDTO.wage() != null) {
            jobEntity.setWage(updateJobDTO.wage());
        }

        if (updateJobDTO.description() != null) {
            jobEntity.setDescription(updateJobDTO.description());
        }

        // Salva as alterações
        JobEntity jobUpdated = jobRepository.save(jobEntity);

        // Retorna a vaga atualizada
        return new JobResponseDTO(
                jobUpdated.getIdJob(),
                jobUpdated.getTitle(),
                jobUpdated.getLevel(),
                jobUpdated.getWage(),
                jobUpdated.getDescription()
        );
    }
}
