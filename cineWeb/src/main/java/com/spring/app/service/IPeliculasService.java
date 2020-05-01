package com.spring.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.app.model.Pelicula;

public interface IPeliculasService {
	List<Pelicula> buscarTodas(); 
	Page<Pelicula> buscarTodas(Pageable page);
	List<Pelicula> buscarActivas();
	List<Pelicula> buscarPorFecha(Date fecha);
	Pelicula buscarPorId(int idPelicula);
	void guardar(Pelicula pelicula);
	List<String> buscarGeneros();
	void eliminar(int idPelicula);
}
