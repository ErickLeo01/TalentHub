package com.erickleo.talenthub_api.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyUpdateDTO(

        @Pattern(
                regexp = ".*\\S.*",
                message = "O nome não pode conter apenas espaços."
        )
        String name,

        @Email(message = "Informe um e-mail válido.")
        @Pattern(
                regexp = ".*\\S.*",
                message = "O e-mail não pode conter apenas espaços."
        )
        String email,

        @Size(
                min = 6,
                message = "A senha deve ter no mínimo 6 caracteres."
        )
        String password,

        @Pattern(
                regexp = ".*\\S.*",
                message = "O CNPJ não pode conter apenas espaços."
        )
        String cnpj,

        String description,

        String address
) {
}
