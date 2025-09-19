package br.com.orbitall.hackathon2025.canonicals;

import java.time.LocalDateTime;
import java.util.UUID;

public record PersonOutput(
        UUID id,
        String fullName,
        int age,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active
) {
}
