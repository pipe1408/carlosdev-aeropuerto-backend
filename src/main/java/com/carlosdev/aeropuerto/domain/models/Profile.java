package com.carlosdev.aeropuerto.domain.models;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private String id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime date_creation;
    private LocalDateTime last_login;
    private String refresh_token;
    private LocalDateTime refresh_token_expiry;
}
