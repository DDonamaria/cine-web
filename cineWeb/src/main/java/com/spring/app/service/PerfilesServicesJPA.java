package com.spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.model.Perfil;
import com.spring.app.repository.PerfilesRepository;

@Service
public class PerfilesServicesJPA implements IPerfilesService {
	
	@Autowired
	private PerfilesRepository repository;
	
	@Override
	public void guardar(Perfil perfil) {
		repository.save(perfil);
	}


}
