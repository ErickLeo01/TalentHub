package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.exception.JobNotUpdateException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobDeleteUseCase {

    @Autowired
    private JobRepository jobRepository;

    public String deleteJob(UUID idJob) {

        JobEntity jobEntity = jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        // Pega o UUID da empresa autenticada pelo JWT.
        String userId = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UUID companyId = UUID.fromString(userId);

        // Verifica se a vaga pertence à empresa autenticada.
        if (!jobEntity.getCompany().getId().equals(companyId)) {
            throw new JobNotUpdateException();
        }

        jobRepository.delete(jobEntity);

        return "Vaga deletada com sucesso.";
    }
}