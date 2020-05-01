package com.spring.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.app.model.Banner;

public interface IBannersService {

	List<Banner> buscarTodos();
	Page<Banner> buscarTodos(Pageable page);
	List<Banner> buscarActivos();
	Banner buscarPorId(int idBanner);
	void guardar(Banner banner); 
	void eliminar(int idBanner);
}
