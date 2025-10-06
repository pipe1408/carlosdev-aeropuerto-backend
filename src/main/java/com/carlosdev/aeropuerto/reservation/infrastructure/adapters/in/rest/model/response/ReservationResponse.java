package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.carlosdev.aeropuerto.reservation.domain.model.FlightModel;
import com.carlosdev.aeropuerto.reservation.domain.model.PassengerModel;
import com.carlosdev.aeropuerto.reservation.domain.model.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationResponse {

	private Long id;
	
	private String code;
	
	private LocalDateTime reservationDateTime;
	
	private StatusEnum status;
	
	private PassengerModel passenger;
	
	private FlightModel flight;
	
	private int seat;
	
	private BigDecimal price;
}
