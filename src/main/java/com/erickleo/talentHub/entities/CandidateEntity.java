package com.erickleo.talentHub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCandidate;

    @NotBlank(message = "O nome do candidato não pode ser nulo!")
    private String name;

    @Email(message = "Insira um e-mail válido!")
    @Column(unique = true)
    @NotBlank(message = "O e-mail não pode ser nulo")
    private String email;

    @NotBlank(message = "A senha não pode ser nula")
    private String password;

    @NotBlank(message = "O CPF não pode ser nulo!")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "A cidade não pode ser nula!")
    private String city;

    @Column(length = 5000)
    private String curriculum;

    @ManyToMany
    @JoinTable(
            name = "application",
            joinColumns = @JoinColumn(name = "idCandidate"),
            inverseJoinColumns = @JoinColumn(name = "idJob")
    )
    private List<JobEntity> jobs;

}
