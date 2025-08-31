package com.carlosdev.aeropuerto.domain.ports.in;

import com.carlosdev.aeropuerto.domain.models.Vuelo;

public interface VueloService {
    Vuelo createVuelo(Vuelo vuelo);
    Vuelo getVueloById(String id);
    Vuelo updateVuelo(Vuelo vuelo);
    void deleteVuelo(Vuelo vuelo);
}
