package com.erickleo.talentHub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCompany;

    @NotBlank(message = "O nome da empresa não pode ser nulo!")
    private String name;

    @NotBlank(message = "O nome do proprietário não pode ser nulo!")
    private String owner;

    @Email(message = "Insira um e-mail válido!")
    @NotBlank(message = "O e-mail não pode ser nulo!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha não pode ser nula!")
    private String password;

    @NotBlank(message = "O CNPJ não pode ser nulo!")
    @Column(unique = true)
    private String cnpj;

    @Column(nullable = true, length = 5000)
    private String description;

    @OneToMany(mappedBy = "companyEntity")
    private List<JobEntity> jobEntity;
}
