package com.erickleo.talentHub.dtos.job;

import java.math.BigDecimal;
import java.util.UUID;

public record JobResponseDTO(UUID idJob, String title, String level, BigDecimal wage, String description) {
}
