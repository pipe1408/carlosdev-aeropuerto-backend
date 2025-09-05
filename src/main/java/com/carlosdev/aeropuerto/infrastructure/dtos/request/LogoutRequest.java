package com.carlosdev.aeropuerto.infrastructure.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(
    @NotBlank String refreshToken
) {}
