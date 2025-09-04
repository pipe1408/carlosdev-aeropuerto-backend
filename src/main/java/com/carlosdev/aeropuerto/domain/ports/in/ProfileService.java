package com.carlosdev.aeropuerto.domain.ports.in;

import com.carlosdev.aeropuerto.domain.models.Profile;

import java.util.List;

public interface ProfileService {
    Profile createUser(Profile user);
    Profile getUserById(String id);
    Profile getUserByEmail(String email);
    List<Profile> getAllUsers();
    Profile updateUser(Profile user);
    void deleteUser(String id);
}
