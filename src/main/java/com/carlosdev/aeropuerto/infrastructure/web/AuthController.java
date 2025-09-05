package com.carlosdev.aeropuerto.infrastructure.web;

import com.carlosdev.aeropuerto.domain.models.Profile;
import com.carlosdev.aeropuerto.domain.ports.in.AuthService;
import com.carlosdev.aeropuerto.infrastructure.dtos.request.LoginRequest;
import com.carlosdev.aeropuerto.infrastructure.dtos.request.RegisterRequest;
import com.carlosdev.aeropuerto.infrastructure.dtos.request.RefreshTokenRequest;
import com.carlosdev.aeropuerto.infrastructure.dtos.request.LogoutRequest;
import com.carlosdev.aeropuerto.infrastructure.dtos.responses.LoginResponse;
import com.carlosdev.aeropuerto.infrastructure.dtos.responses.RegisterResponse;
import com.carlosdev.aeropuerto.infrastructure.dtos.responses.RefreshTokenResponse;
import com.carlosdev.aeropuerto.infrastructure.dtos.responses.LogoutResponse;
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
        AuthService.LoginResult result = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new LoginResponse(result.accessToken(), result.refreshToken()));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        Profile user = authService.register(request.email(), request.password(), request.name());
        return ResponseEntity.ok(new RegisterResponse(user.getName(), user.getEmail(), user.getDate_creation()));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthService.RefreshResult result = authService.refreshToken(request.refreshToken());
        return ResponseEntity.ok(new RefreshTokenResponse(result.accessToken(), result.refreshToken()));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@Valid @RequestBody LogoutRequest request) {
        authService.logout(request.refreshToken());
        return ResponseEntity.ok(new LogoutResponse("Logout exitoso"));
    }
}

