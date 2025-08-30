package com.carlosdev.aeropuerto.domain.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vuelo {
    private String id;
    private String origen;
    private String destino;
    private Integer asientosDisponibles;
    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;
}
