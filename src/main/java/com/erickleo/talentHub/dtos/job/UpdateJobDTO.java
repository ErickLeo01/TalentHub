package com.erickleo.talentHub.dtos.job;

import java.math.BigDecimal;

public record UpdateJobDTO(String title, String level, BigDecimal wage, String description) {
}
