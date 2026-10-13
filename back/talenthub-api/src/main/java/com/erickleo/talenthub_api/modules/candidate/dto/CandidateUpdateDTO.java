package com.erickleo.talenthub_api.modules.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CandidateUpdateDTO(

        String name,

        @Email(message = "Informe um e-mail válido.")
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,

        String cpf,

        String description
) {
}
