package com.carlosdev.aeropuerto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.carlosdev.aeropuerto.reservation.domain.model.StatusEnum;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.FlightEntity;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.PassengerEntity;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.ReservationEntity;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository.FlightRepository;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository.PassengerRepository;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class AeropuertoApplication implements CommandLineRunner {
	
	private final ReservationRepository reservationRepository;
	private final PassengerRepository passengerRepository;
	private final FlightRepository flightRepository;

	public static void main(String[] args) {
		SpringApplication.run(AeropuertoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	    // --- Pasajeros ---
	    PassengerEntity p1 = new PassengerEntity();
	    p1.setName("Juan Pérez");

	    PassengerEntity p2 = new PassengerEntity();
	    p2.setName("María López");

	    // --- Vuelos ---
	    FlightEntity f1 = new FlightEntity();
	    f1.setName("Vuelo AR123 - Buenos Aires a Córdoba");

	    FlightEntity f2 = new FlightEntity();
	    f2.setName("Vuelo AR456 - Mendoza a Salta");

	    // Guardar pasajeros y vuelos primero
	    passengerRepository.saveAll(List.of(p1, p2));
	    flightRepository.saveAll(List.of(f1, f2));

	    // --- Reservas ---
	    ReservationEntity r1 = new ReservationEntity();
	    r1.setCode("RES-001");
	    r1.setReservationDateTime(LocalDateTime.now().plusDays(5));
	    r1.setStatus(StatusEnum.PENDING);
	    r1.setPassenger(p1);
	    r1.setFlight(f1);
	    r1.setSeat(12);
	    r1.setPrice(BigDecimal.valueOf(25000));

	    ReservationEntity r2 = new ReservationEntity();
	    r2.setCode("RES-002");
	    r2.setReservationDateTime(LocalDateTime.now().plusDays(10));
	    r2.setStatus(StatusEnum.CONFIRMED);
	    r2.setPassenger(p2);
	    r2.setFlight(f2);
	    r2.setSeat(8);
	    r2.setPrice(BigDecimal.valueOf(32000));

	    ReservationEntity r3 = new ReservationEntity();
	    r3.setCode("RES-003");
	    r3.setReservationDateTime(LocalDateTime.now().plusDays(3));
	    r3.setStatus(StatusEnum.CANCELED);
	    r3.setPassenger(p1);
	    r3.setFlight(f2);
	    r3.setSeat(15);
	    r3.setPrice(BigDecimal.valueOf(28000));

	    reservationRepository.saveAll(List.of(r1, r2, r3));

	    System.out.println("Datos de prueba cargados correctamente.");
	}

}
