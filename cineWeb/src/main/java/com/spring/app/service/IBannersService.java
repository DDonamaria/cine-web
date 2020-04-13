package com.spring.app.service;

import java.util.List;

import com.spring.app.model.Banner;

public interface IBannersService {
	List<Banner> buscarTodos();
	List<Banner> buscarTodosActivos();
	void crearBanner(Banner banner);

}
