package com.spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.model.Detalle;
import com.spring.app.repository.DetallesRepository;

@Service
public class DetallesServicesJPA implements IDetallesService {
	
	@Autowired
	DetallesRepository detallesRepository;
		
	@Override
	public void guardar(Detalle detalle) {
		detallesRepository.save(detalle);
	}

	@Override
	public void eliminar(int idDetalle) {
		detallesRepository.deleteById(idDetalle);
	}

}
