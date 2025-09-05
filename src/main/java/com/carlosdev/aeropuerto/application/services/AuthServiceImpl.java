package com.carlosdev.aeropuerto.application.services;

import com.carlosdev.aeropuerto.domain.models.Profile;
import com.carlosdev.aeropuerto.domain.ports.in.AuthService;
import com.carlosdev.aeropuerto.domain.ports.out.ProfileRepository;
import com.carlosdev.aeropuerto.infrastructure.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final ProfileRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    // Sera remplazado por una base de datos o cache
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    @Override
    public LoginResult login(String email, String password) {
        Profile user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
        
        user.setLast_login(LocalDateTime.now());
        user.setRefresh_token(refreshToken);
        user.setRefresh_token_expiry(LocalDateTime.now().plusSeconds(604800));
        userRepository.save(user);
        
        return new LoginResult(accessToken, refreshToken);
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
    public void logout(String refreshToken) {
        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token expirado");
        }
        
        String email = jwtUtil.extractUsername(refreshToken);
        Profile user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        if (!refreshToken.equals(user.getRefresh_token())) {
            throw new RuntimeException("Refresh token inválido");
        }
        
        blacklistedTokens.add(refreshToken);
        user.setRefresh_token(null);
        user.setRefresh_token_expiry(null);
        userRepository.save(user);
    }

    @Override
    public Profile getCurrentUser(String token) {
        String email = jwtUtil.extractUsername(token);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    @Override
    public RefreshResult refreshToken(String refreshToken) {
        if (blacklistedTokens.contains(refreshToken)) {
            throw new RuntimeException("Refresh token invalidado");
        }
        
        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token expirado");
        }
        
        String email = jwtUtil.extractUsername(refreshToken);
        Profile user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
        if (!refreshToken.equals(user.getRefresh_token()) || 
            user.getRefresh_token_expiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token inválido");
        }
        
        String newAccessToken = jwtUtil.generateToken(user.getEmail());
        String newRefreshToken = jwtUtil.generateRefreshToken(user.getEmail());
        
        blacklistedTokens.add(refreshToken);
        user.setRefresh_token(newRefreshToken);
        user.setRefresh_token_expiry(LocalDateTime.now().plusSeconds(604800));
        userRepository.save(user);
        
        return new RefreshResult(newAccessToken, newRefreshToken);
    }
}

