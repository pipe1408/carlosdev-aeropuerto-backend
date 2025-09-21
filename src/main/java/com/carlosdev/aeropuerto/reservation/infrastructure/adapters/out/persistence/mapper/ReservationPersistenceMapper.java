package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.carlosdev.aeropuerto.reservation.domain.model.ReservationModel;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.ReservationEntity;

@Mapper(componentModel = "spring")
public interface ReservationPersistenceMapper {

	ReservationEntity toReservationEntity(ReservationModel reservationModel);
	
	ReservationModel toReservationModel(ReservationEntity reservationEntity);
	
	List<ReservationModel> toListReservationModels(List<ReservationEntity> listReservationEntities);
}
