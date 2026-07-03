package com.erickleo.talentHub.dtos.job;

import java.util.UUID;

public record JobResponseDTO(UUID idJob, String title, String level, String wage, String description) {
}
