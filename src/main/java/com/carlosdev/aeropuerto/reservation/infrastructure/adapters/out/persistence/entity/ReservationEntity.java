package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.carlosdev.aeropuerto.reservation.domain.model.StatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_reservation")
public class ReservationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	
	@Column(name = "reservation_date_time")
	private LocalDateTime reservationDateTime;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id", nullable = false)
	private PassengerEntity passenger;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = false)
	private FlightEntity flight;
	
	private int seat;
	
	private BigDecimal price;
}
