package com.erickleo.talentHub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idJob;

    @NotBlank(message = "O título da vaga não pode ser nulo!")
    private String title;

    @NotBlank(message = "O nível da vaga não pode ser nulo!")
    private String level;

    @NotBlank(message = "O salário não pode ser nulo!")
    private BigDecimal wage;

    @Column(length = 5000)
    @NotBlank(message = "A descrição da vaga não pode ser nula!")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "idCompany")
    private CompanyEntity companyEntity;
}
