package com.carlosdev.aeropuerto.reservation.application.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.carlosdev.aeropuerto.reservation.application.ports.in.ReservationServicePort;
import com.carlosdev.aeropuerto.reservation.application.ports.out.ReservationPersistencePort;
import com.carlosdev.aeropuerto.reservation.domain.exception.ReservationNotFoundException;
import com.carlosdev.aeropuerto.reservation.domain.model.ReservationModel;
import com.carlosdev.aeropuerto.reservation.domain.model.StatusEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationServicePort {
	
	private final ReservationPersistencePort reservationPersistencePort;
	
	@Override
	public ReservationModel createReservation(ReservationModel reservationModel) {
		return reservationPersistencePort.save(reservationModel);
	}

	@Override
	public List<ReservationModel> findAllReservation() {
		return reservationPersistencePort.findAll();
	}

	@Override
	public ReservationModel findReservationById(Long id) throws ReservationNotFoundException {
		return reservationPersistencePort.findById(id)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation with ID: " + id + " not found"));
	}

	@Override
	public ReservationModel patchReservationById(Long id, Map<String, Object> updates) throws ReservationNotFoundException {
		ReservationModel reservation = reservationPersistencePort.findById(id)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation with ID: " + id + " not found"));
		
		updates.forEach((key, value) -> {
			switch (key) {
			case "code" -> reservation.setCode((String) value);
			case "status" -> reservation.setStatus((StatusEnum) value);
			case "seat" -> reservation.setSeat((int) value);
			case "price" -> reservation.setPrice((BigDecimal) value);
			}
		});
		
		return reservationPersistencePort.save(reservation);
	}

	@Override
	public void deleteReservationById(Long id) throws ReservationNotFoundException {
		if (reservationPersistencePort.findById(id).isEmpty()) {
			throw new ReservationNotFoundException("Reservation with ID: " + id + " not found");
		}
		reservationPersistencePort.deleteById(id);
	}

}
