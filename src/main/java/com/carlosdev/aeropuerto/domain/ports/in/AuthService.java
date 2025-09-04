package com.carlosdev.aeropuerto.domain.ports.in;

import com.carlosdev.aeropuerto.domain.models.Profile;

public interface AuthService {
    String login(String email, String password);
    Profile register(String email, String password, String name);
    void logout(String token);
    Profile getCurrentUser(String token);
}

