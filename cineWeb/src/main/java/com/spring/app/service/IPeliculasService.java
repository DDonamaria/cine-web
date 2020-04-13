package com.spring.app.service;

import java.util.List;

import com.spring.app.model.Pelicula;

public interface IPeliculasService {
	List<Pelicula> buscarTodas(); 
	Pelicula buscarPorId(int idPelicula);
	void crearPelicula(Pelicula pelicula);
	List<String> buscarGeneros();

}
