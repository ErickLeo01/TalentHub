package com.erickleo.talenthub_api.modules.job.controller;

import com.erickleo.talenthub_api.modules.job.dto.JobDTO;
import com.erickleo.talenthub_api.modules.job.dto.JobResponseDTO;
import com.erickleo.talenthub_api.modules.job.service.JobCreateUseCase;
import com.erickleo.talenthub_api.modules.job.service.JobDeleteUseCase;
import com.erickleo.talenthub_api.modules.job.service.JobFindByNameUseCase;
import com.erickleo.talenthub_api.modules.job.service.JobUpdateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vagas")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {

    @Autowired
    private JobCreateUseCase jobCreateUseCase;

    @Autowired
    private JobDeleteUseCase jobDeleteUseCase;

    @Autowired
    private JobUpdateUseCase jobUpdateUseCase;

    @Autowired
    private JobFindByNameUseCase jobFindByNameUseCase;

    @PostMapping("/criar")
    public JobResponseDTO createJob(@Valid @RequestBody JobDTO jobDTO) {
        return jobCreateUseCase.createJob(jobDTO);
    }

    @PutMapping("/atualizar/{id}")
    public JobResponseDTO updateJob(
            @PathVariable UUID id,
            @Valid @RequestBody JobDTO jobDTO) {

        return jobUpdateUseCase.updateJob(id, jobDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public String deleteJob(@PathVariable UUID id) {
        return jobDeleteUseCase.deleteJob(id);
    }

    @GetMapping("/buscar")
    public List<JobResponseDTO> searchJobs(@RequestParam String name) {
        return jobFindByNameUseCase.showJob(name);
    }
}