package com.carlosdev.aeropuerto.reservation.application.ports.in;

import java.util.List;
import java.util.Map;

import com.carlosdev.aeropuerto.reservation.domain.exception.ReservationNotFoundException;
import com.carlosdev.aeropuerto.reservation.domain.model.ReservationModel;

public interface ReservationServicePort {

	ReservationModel createReservation(ReservationModel reservationModel);
	
	List<ReservationModel> findAllReservation();
	
	ReservationModel findReservationById(Long id) throws ReservationNotFoundException;
	
	ReservationModel patchReservationById(Long id, Map<String, Object> updates) throws ReservationNotFoundException;
	
	void deleteReservationById(Long id) throws ReservationNotFoundException;
}
