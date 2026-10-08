package com.erickleo.talenthub_api.modules.candidate.service;

import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.candidate.exception.CandidateNotFoundException;
import com.erickleo.talenthub_api.modules.candidate.repository.CandidateRepository;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import com.erickleo.talenthub_api.modules.job.exception.JobNotFoundException;
import com.erickleo.talenthub_api.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CandidateApplyJobUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public String applyJob(UUID jobId) {

        // Pega o UUID do candidato autenticado pelo JWT.
        String userId = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        UUID candidateId = UUID.fromString(userId);

        // Busca o candidato.
        CandidateEntity candidateEntity = candidateRepository.findById(candidateId)
                .orElseThrow(CandidateNotFoundException::new);

        // Busca a vaga.
        JobEntity jobEntity = jobRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);

        // Cria a lista caso o candidato ainda não tenha vagas.
        if (candidateEntity.getJobs() == null) {
            candidateEntity.setJobs(new ArrayList<>());
        }

        // Verifica se o candidato já se candidatou.
        if (candidateEntity.getJobs().contains(jobEntity)) {
            return "Você já se candidatou a esta vaga.";
        }

        // Adiciona a vaga à lista de vagas do candidato.
        candidateEntity.getJobs().add(jobEntity);

        // Salva a candidatura.
        candidateRepository.save(candidateEntity);

        return "Candidatura realizada com sucesso.";
    }
}