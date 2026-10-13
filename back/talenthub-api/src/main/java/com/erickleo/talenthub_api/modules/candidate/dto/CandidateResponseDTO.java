package com.erickleo.talenthub_api.modules.candidate.dto;

import java.util.UUID;

public record CandidateResponseDTO(UUID id, String name, String email, String description) {
}
