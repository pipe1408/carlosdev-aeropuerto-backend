package com.carlosdev.aeropuerto.infrastructure.security;

import com.carlosdev.aeropuerto.domain.models.Profile;
import com.carlosdev.aeropuerto.domain.ports.out.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final ProfileRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(Collections.emptyList())
            .build();
    }
}

