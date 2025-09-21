package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{

}
