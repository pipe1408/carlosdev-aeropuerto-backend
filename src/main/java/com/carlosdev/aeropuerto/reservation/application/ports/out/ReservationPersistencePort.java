package com.carlosdev.aeropuerto.reservation.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.carlosdev.aeropuerto.reservation.domain.model.ReservationModel;

public interface ReservationPersistencePort {

	ReservationModel save(ReservationModel reservationModel);
	
	List<ReservationModel> findAll();
	
	Optional<ReservationModel> findById(Long id);
	
	void deleteById(Long id);
}
