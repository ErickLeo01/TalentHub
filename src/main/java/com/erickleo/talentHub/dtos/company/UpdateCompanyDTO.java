package com.erickleo.talentHub.dtos.company;

public record UpdateCompanyDTO(String name, String owner, String email, String password, String cnpj, String description) {
}
