package com.carlosdev.aeropuerto.infrastructure.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
    @NotBlank String refreshToken
) {}
