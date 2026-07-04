package com.erickleo.talentHub.dtos.candidate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCandidateDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(min = 3, max = 100)
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,

        @NotBlank(message = "CPF não pode ser vazio")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
        String cpf,

        @NotBlank(message = "Cidade não pode ser vazia")
        String city,

        String curriculum
){ }
