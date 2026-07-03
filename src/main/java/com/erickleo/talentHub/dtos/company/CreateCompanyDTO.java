package com.erickleo.talentHub.dtos.company;

public record CreateCompanyDTO(String name, String owner, String email, String password, String cnpj, String description) {
}
