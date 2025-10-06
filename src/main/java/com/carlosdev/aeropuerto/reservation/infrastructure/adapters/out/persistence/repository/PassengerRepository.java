package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.PassengerEntity;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {

}
