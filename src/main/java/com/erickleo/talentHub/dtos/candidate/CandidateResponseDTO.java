package com.erickleo.talentHub.dtos.candidate;

import java.util.UUID;

public record CandidateResponseDTO(UUID idCandidate, String name, String email, String city, String curriculum) {
}
