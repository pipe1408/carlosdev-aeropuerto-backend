package com.carlosdev.aeropuerto.domain.ports.out;

import com.carlosdev.aeropuerto.domain.models.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    Profile save(Profile user);
    Optional<Profile> findById(String id);
    Optional<Profile> findByEmail(String email);
    List<Profile> findAll();
    void deleteById(String id);
    boolean existsByEmail(String email);
}
