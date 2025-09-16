package com.carlosdev.aeropuerto.reservation.domain.model;

import static com.carlosdev.aeropuerto.reservation.domain.model.StatusEnum.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationModel {
	private Long id;
	private String code;
	private LocalDateTime reservationDateTime;
	private StatusEnum status;
	private PassengerModel passenger;
	private FlightModel flight;
	private int seat;
	private BigDecimal price;
		
    public void confirm() {
        if (status == CANCELED) {
            throw new IllegalStateException("No se puede confirmar una reserva cancelada.");
        }
        this.status = CONFIRMED;
    }

    public void cancel() {
        if (status == CONFIRMED
                && reservationDateTime.isBefore(LocalDateTime.now().plusHours(24))) {
            throw new IllegalStateException("No se puede cancelar una reserva confirmada a menos de 24h del vuelo.");
        }
        this.status = CANCELED;
    }
    
    public void assignSeat(int seatNumber) {
        if (status == CANCELED) {
            throw new IllegalStateException("No se puede asignar asiento a una reserva cancelada.");
        }
        this.seat = seatNumber;
    }
    
    public boolean isActive() {
        return status == CONFIRMED || status == PENDING;
    }
    
    public BigDecimal calculateCancellationFee() {
        if (reservationDateTime.isAfter(LocalDateTime.now().plusDays(7))) {
            return BigDecimal.ZERO; 
        }
        return price.multiply(BigDecimal.valueOf(0.10)); // 10% 
    }
}
