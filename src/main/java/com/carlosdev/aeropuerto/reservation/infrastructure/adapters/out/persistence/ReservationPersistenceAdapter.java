package com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.carlosdev.aeropuerto.reservation.application.ports.out.ReservationPersistencePort;
import com.carlosdev.aeropuerto.reservation.domain.model.ReservationModel;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.entity.ReservationEntity;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.mapper.ReservationPersistenceMapper;
import com.carlosdev.aeropuerto.reservation.infrastructure.adapters.out.persistence.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationPersistenceAdapter implements ReservationPersistencePort {

	private final ReservationRepository repository;
	private final ReservationPersistenceMapper mapper;

	@Override
	public ReservationModel save(ReservationModel reservationModel) {
		ReservationEntity reservationEntity = repository.save(mapper.toReservationEntity(reservationModel));
		return mapper.toReservationModel(reservationEntity);
	}

	@Override
	public List<ReservationModel> findAll() {
		return mapper.toListReservationModels(repository.findAll());
	}

	@Override
	public Optional<ReservationModel> findById(Long id) {
		return repository.findById(id).map(mapper::toReservationModel);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
