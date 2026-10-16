package com.erickleo.talenthub_api.modules.job.dto;

import java.util.UUID;

public record JobResponseDTO(UUID id, String name, String description, double salary, String benefits) {
}