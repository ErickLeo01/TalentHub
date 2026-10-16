package com.erickleo.talenthub_api.modules.job.service;

import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.exception.JobNotUpdateException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_COMPANY".equals(authority.getAuthority()))) {
            throw new JobNotUpdateException();
        }

        UUID companyId;

        try {
            companyId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException exception) {
            throw new JobNotUpdateException();
        }

        // Verifica se a vaga pertence à empresa autenticada.
        if (jobEntity.getCompany() == null
                || !jobEntity.getCompany().getId().equals(companyId)) {
            throw new JobNotUpdateException();
        }

        // Impede a exclusão se houver candidatos vinculados.
        if (jobEntity.getCandidates() != null
                && !jobEntity.getCandidates().isEmpty()) {
            throw new JobNotUpdateException();
        }

        jobRepository.delete(jobEntity);

        return "Vaga deletada com sucesso.";
    }
}
