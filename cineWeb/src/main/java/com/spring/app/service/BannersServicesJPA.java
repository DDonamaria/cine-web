package com.spring.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.app.model.Banner;
import com.spring.app.repository.BannersRepository;

@Service
public class BannersServicesJPA implements IBannersService {
	
	@Autowired
	private BannersRepository bannersRepository;

	@Override
	public List<Banner> buscarTodos() {
		return bannersRepository.findAll();
	}
	
	@Override
	public Page<Banner> buscarTodos(Pageable page) {
		return bannersRepository.findAll(page);
	}

	@Override
	public List<Banner> buscarActivos() {
		return bannersRepository.findByEstatusOrderByIdDesc("Activo");
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		Optional<Banner> optional = bannersRepository.findById(idBanner);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}
	
	@Override
	public void guardar(Banner banner) {
		bannersRepository.save(banner);
	}

	@Override
	public void eliminar(int idBanner) {
		bannersRepository.deleteById(idBanner);		
	}

}
