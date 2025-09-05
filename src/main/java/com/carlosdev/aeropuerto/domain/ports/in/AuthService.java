package com.carlosdev.aeropuerto.domain.ports.in;

import com.carlosdev.aeropuerto.domain.models.Profile;

public interface AuthService {
    LoginResult login(String email, String password);
    Profile register(String email, String password, String name);
    void logout(String refreshToken);
    Profile getCurrentUser(String token);
    RefreshResult refreshToken(String refreshToken);
    
    record LoginResult(String accessToken, String refreshToken) {}
    record RefreshResult(String accessToken, String refreshToken) {}
}

