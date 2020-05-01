package com.spring.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.app.model.Horario;
import com.spring.app.repository.HorariosRepository;

@Service
public class HorariosServicesJPA implements IHorariosService {
	
	@Autowired
	private HorariosRepository horariosRepository;

	@Override
	public List<Horario> buscarTodos() {
		return horariosRepository.findAll();
	}

	@Override
	public Page<Horario> buscarTodos(Pageable page) {
		return horariosRepository.findAll(page);
	}

	@Override
	public Horario buscarPorId(int idHorario) {
		Optional<Horario> optional = horariosRepository.findById(idHorario);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return horariosRepository.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}

	@Override
	public void guardar(Horario horario) {
		horariosRepository.save(horario);
	}

	@Override
	public void eliminar(int idHorario) {
		horariosRepository.deleteById(idHorario);
	}

}
