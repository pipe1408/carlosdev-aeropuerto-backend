package com.carlosdev.aeropuerto.application;

import com.carlosdev.aeropuerto.domain.models.Profile;
import com.carlosdev.aeropuerto.domain.ports.in.AuthService;
import com.carlosdev.aeropuerto.domain.ports.out.ProfileRepository;
import com.carlosdev.aeropuerto.infrastructure.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final ProfileRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String login(String email, String password) {
        Profile user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        user.setLast_login(LocalDateTime.now());
        userRepository.save(user);
        
        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public Profile register(String email, String password, String name) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email ya registrado");
        }
        
        Profile user = Profile.builder()
            .id(UUID.randomUUID().toString())
            .email(email)
            .password(passwordEncoder.encode(password))
            .name(name)
            .date_creation(LocalDateTime.now())
            .build();
            
        return userRepository.save(user);
    }

    @Override
    public void logout(String token) {
        
    }

    @Override
    public Profile getCurrentUser(String token) {
        String email = jwtUtil.extractUsername(token);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}

