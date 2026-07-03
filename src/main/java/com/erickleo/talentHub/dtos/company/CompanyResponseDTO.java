package com.erickleo.talentHub.dtos.company;

import java.util.UUID;

public record CompanyResponseDTO(UUID idCompany, String name, String owner, String email, String cnpj, String description) {
}
