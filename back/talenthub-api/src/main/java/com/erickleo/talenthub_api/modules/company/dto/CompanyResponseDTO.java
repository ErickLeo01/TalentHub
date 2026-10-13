package com.erickleo.talenthub_api.modules.company.dto;

import java.util.UUID;

public record CompanyResponseDTO(UUID id, String name, String email, String description, String address, String cnpj) {
}
