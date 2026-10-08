package com.erickleo.talenthub_api.modules.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record JobDTO(

        @NotBlank(message = "O nome da vaga é obrigatório.")
        String name,

        @NotBlank(message = "A descrição da vaga é obrigatória.")
        String description,

        @NotNull(message = "O salário é obrigatório.")
        @Positive(message = "O salário deve ser maior que zero.")
        Double salary,

        @NotBlank(message = "Os benefícios são obrigatórios.")
        String benefits
) {
}