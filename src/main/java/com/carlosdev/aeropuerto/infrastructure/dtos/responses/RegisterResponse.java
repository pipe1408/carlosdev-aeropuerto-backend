package com.carlosdev.aeropuerto.infrastructure.dtos.responses;

import java.time.LocalDateTime;

public record RegisterResponse(String name, String email, LocalDateTime date_creation) {}
