package org.adaschool.bookingproject.domain.dto;

public record AuthenticationDto(
        String email,
        String password
) {
}
