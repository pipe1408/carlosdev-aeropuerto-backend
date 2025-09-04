package com.carlosdev.aeropuerto.infrastructure.web;

import com.carlosdev.aeropuerto.domain.models.Profile;
import com.carlosdev.aeropuerto.domain.ports.in.AuthService;
import com.carlosdev.aeropuerto.infrastructure.dtos.request.LoginRequest;
import com.carlosdev.aeropuerto.infrastructure.dtos.request.RegisterRequest;
import com.carlosdev.aeropuerto.infrastructure.dtos.responses.LoginResponse;
import com.carlosdev.aeropuerto.infrastructure.dtos.responses.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        Profile usuario = authService.register(request.email(), request.password(), request.name());
        return ResponseEntity.ok(new RegisterResponse(usuario.getName(), usuario.getEmail(), usuario.getDate_creation()));
    }
}

