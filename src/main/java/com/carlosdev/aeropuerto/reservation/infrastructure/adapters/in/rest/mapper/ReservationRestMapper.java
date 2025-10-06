package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.carlosdev.aeropuerto.reservation.domain.model.ReservationModel;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest.model.request.ReservationCreateRequest;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.in.rest.model.response.ReservationResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationRestMapper {

	ReservationModel toReservationModel(ReservationCreateRequest reservationCreateRequest);
	
	ReservationResponse toReservationResponse(ReservationModel reservationModel);
	
	List<ReservationResponse> toReservationResponses(List<ReservationModel> reservationModels);
}
