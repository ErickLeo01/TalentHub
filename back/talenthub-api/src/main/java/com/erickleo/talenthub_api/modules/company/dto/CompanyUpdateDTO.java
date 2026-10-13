package com.erickleo.talenthub_api.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CompanyUpdateDTO(

        String name,

        @Email(message = "Informe um e-mail válido.")
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,

        String cnpj,

        String description,

        String address
) {
}
