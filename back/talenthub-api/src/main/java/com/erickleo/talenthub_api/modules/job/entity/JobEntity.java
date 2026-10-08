package com.erickleo.talenthub_api.modules.job.entity;

import com.erickleo.talenthub_api.modules.candidate.entity.CandidateEntity;
import com.erickleo.talenthub_api.modules.company.entity.CompanyEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "jobs")
@Entity
public class JobEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O nome da vaga não pode ser nulo.")
    private String name;

    @NotBlank(message = "A descrição da vaga não pode ser nulo.")
    private String description;

    @NotNull(message = "O salário da vaga não pode ser nulo.")
    private double salary;

    @NotBlank(message = "Os benefícios da vaga não pode ser nulo.")
    private String benefits;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToMany(mappedBy = "jobs")
    private List<CandidateEntity> candidates;
}
