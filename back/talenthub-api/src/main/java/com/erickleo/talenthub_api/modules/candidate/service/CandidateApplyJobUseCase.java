package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class CandidateApplyJobUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Transactional
    public String applyJob(UUID jobId) {

        // Obtém o usuário autenticado.
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        // Confere se é um candidato autenticado.
        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null
                || authentication.getAuthorities().stream()
                .noneMatch(authority ->
                        "ROLE_CANDIDATE".equals(authority.getAuthority()))) {
            throw new IllegalStateException(
                    "Somente candidatos autenticados podem se candidatar."
            );
        }

        // Obtém o UUID do candidato pelo token.
        UUID candidateId = UUID.fromString(authentication.getName());

        // Busca o candidato.
        CandidateEntity candidateEntity =
                candidateRepository.findById(candidateId)
                        .orElseThrow(CandidateNotFoundException::new);

        // Busca a vaga.
        JobEntity jobEntity = jobRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);

        // Inicializa a lista, se necessário.
        if (candidateEntity.getJobs() == null) {
            candidateEntity.setJobs(new ArrayList<>());
        }

        // Impede uma candidatura duplicada nesta verificação.
        if (candidateEntity.getJobs().contains(jobEntity)) {
            return "Você já se candidatou a esta vaga.";
        }

        // Registra a candidatura.
        candidateEntity.getJobs().add(jobEntity);
        candidateRepository.save(candidateEntity);

        return "Candidatura realizada com sucesso.";
    }
}
