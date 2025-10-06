package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long>{

}
