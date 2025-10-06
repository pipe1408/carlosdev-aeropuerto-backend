package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosdev.aeropuerto.reservation.application.ports.in.ReservationServicePort;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest.mapper.ReservationRestMapper;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest.model.response.ReservationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationRestAdapter {

	private final ReservationServicePort reservationServicePort;
	private final ReservationRestMapper mapper;
	
	@GetMapping("/v1/api")
	public ResponseEntity<List<ReservationResponse>> findAllReservation() {
		List<ReservationResponse> response = mapper.toReservationResponses(reservationServicePort.findAllReservation());
		return new ResponseEntity<List<ReservationResponse>>(response, HttpStatus.OK);
	}
}
