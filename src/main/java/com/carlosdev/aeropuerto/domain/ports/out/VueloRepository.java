package com.carlosdev.aeropuerto.domain.ports.out;

import com.carlosdev.aeropuerto.domain.models.Vuelo;

public interface VueloRepository {
    Vuelo saveVuelo(Vuelo vuelo);
    Vuelo findVueloById(String id);
    void deleteVuelo(String id);
}
