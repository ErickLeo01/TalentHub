package com.erickleo.talenthub_api.modules.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.erickleo.talenthub_api.modules.job.entity.JobEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "company")
@Entity
public class CompanyEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O nome da empresa não pode ser nulo.")
    private String name;

    @NotBlank(message = "O e-mail não pode ser nulo.")
    @Email(message = "Insira um e-mail válido.")
    private String email;

    @NotBlank(message = "A senha não pode ser nula.")
    private String password;

    @NotBlank(message = "O CNPJ não pode ser nulo.")
    private String cnpj;

    @NotBlank(message = "A descrição não pode ser nula.")
    private String description;

    @NotBlank(message = "O endereço não pode ser nulo.")
    private String address;

    @OneToMany(mappedBy = "company")
    private List<JobEntity> jobs;
}
