package br.com.orbitall.hackathon2025.canonicals;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonInput(
        @NotBlank(message = "Full name cannot be null or empty")
        @Size(min = 1, max = 255, message = "Full name must be between 1 and 255 characters")
        String fullName,

        int age,

        String description
) {
}
