package com.erickleo.talentHub.controllers.job;

import com.erickleo.talentHub.dtos.job.CreateJobDTO;
import com.erickleo.talentHub.dtos.job.JobResponseDTO;
import com.erickleo.talentHub.dtos.job.UpdateJobDTO;
import com.erickleo.talentHub.entities.JobEntity;
import com.erickleo.talentHub.useCases.job.CreateJobUseCase;
import com.erickleo.talentHub.useCases.job.DeleteJobUseCase;
import com.erickleo.talentHub.useCases.job.UpdateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vaga")
public class JobController {

    @Autowired
    private CreateJobUseCase  createJobUseCase;

    @Autowired
    private UpdateJobUseCase updateJobUseCase;

    @Autowired
    private DeleteJobUseCase deleteJobUseCase;

    @PostMapping("/criarVaga")
    public JobResponseDTO createJob(@Valid @RequestBody CreateJobDTO createJobDTO) {

        return createJobUseCase.execute(createJobDTO);
    }

    @PutMapping("/{id}")
    public JobResponseDTO updateJob(@Valid @PathVariable("id") UUID idJob, UpdateJobDTO updateJobDTO) {

        return updateJobUseCase.execute(idJob, updateJobDTO);
    }

    @DeleteMapping("/excluirVaga/{id}")
    public String deleteJob(@Valid @PathVariable("id") UUID idJob) {

        return deleteJobUseCase.execute(idJob);
    }
}
