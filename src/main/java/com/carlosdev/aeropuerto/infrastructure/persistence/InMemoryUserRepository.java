package com.carlosdev.aeropuerto.infrastructure.persistence;

import com.carlosdev.aeropuerto.domain.models.Profile;
import com.carlosdev.aeropuerto.domain.ports.out.ProfileRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Esto tendra que ser reemplazado por una implementacion real con base de datos en PostgreSQL
// Esta clase es solo para momentaneamente simular la persistencia de datos en memoria
@Repository
public class InMemoryUserRepository implements ProfileRepository {
    
    private final Map<String, Profile> users = new ConcurrentHashMap<>();

    @Override
    public Profile save(Profile user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<Profile> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        return users.values().stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst();
    }

    @Override
    public List<Profile> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.values().stream()
            .anyMatch(user -> user.getEmail().equals(email));
    }
}


