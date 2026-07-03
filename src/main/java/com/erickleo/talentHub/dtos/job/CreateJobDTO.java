package com.erickleo.talentHub.dtos.job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateJobDTO(

        @NotBlank(message = "Título não pode ser vazio")
        @Size(min = 3, max = 100)
        String title,

        @NotBlank(message = "Nível da vaga não pode ser vazio")
        @Size(min = 3, max = 100)
        String level,

        @NotNull(message = "O salário não pode ser nulo!")
        String wage,

        @Size(max = 5000)
        String description) {
}
