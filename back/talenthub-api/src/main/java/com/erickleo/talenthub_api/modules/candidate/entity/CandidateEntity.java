package com.erickleo.talenthub_api.modules.candidate.entity;

import com.erickleo.talenthub_api.modules.job.entity.JobEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "candidates")
@Entity
public class CandidateEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O nome do candidato não pode ser nulo.")
    private String name;

    @Email(message = "Insira um e-mail válido.")
    @NotBlank(message = "O e-mail não pode ser nulo.")
    private String email;

    @NotBlank(message = "A senha não pode ser nula.")
    private String password;

    @NotBlank(message = "O CPF não pode ser nulo.")
    private String cpf;

    @NotBlank(message = "A descrição não pode ser nula.")
    @Lob
    private String description;

    @ManyToMany
    @JoinTable(
            name = "candidates_jobs",
            joinColumns = @JoinColumn(name = "candidates_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )

    private List<JobEntity> jobs;
}
