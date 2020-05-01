package com.spring.app.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.app.model.Horario;
import com.spring.app.model.Pelicula;
import com.spring.app.repository.HorariosRepository;
import com.spring.app.repository.PeliculasRepository;

//Registramos esta clase como un Bean en nuestro Root ApplicationContext.
@Service
public class PeliculasServicesJPA implements IPeliculasService {
	
	// Inyectamos las instancias desde nuestro Root ApplicationContext.
	@Autowired
	private PeliculasRepository peliculasRepository;

	@Autowired	
	private HorariosRepository horariosRepository;
	
	@Override
	public List<Pelicula> buscarTodas() {
		return peliculasRepository.findAll();
	}
	
	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		return peliculasRepository.findAll(page);
	}
	
	@Override
	public List<Pelicula> buscarActivas() {
		return peliculasRepository.findByEstatus_OrderByTitulo("Activa");
	}
	
	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {		
		List<Pelicula> peliculas = null;
		// Buscamos en la tabla de horarios, [agrupando por idPelicula]
		List<Horario> horarios = horariosRepository.findByFecha(fecha);
		peliculas = new LinkedList<>();

		// Formamos la lista final de Peliculas que regresaremos.
		for (Horario h : horarios) {
			// Solo nos interesa de cada registro de horario, el registro de pelicula.
			peliculas.add(h.getPelicula());
		}		
		return peliculas;
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		Optional<Pelicula> optionalPeli = peliculasRepository.findById(idPelicula);
		if(optionalPeli.isPresent()) {
			return optionalPeli.get();
		}
		return null;
	}

	@Override
	public void guardar(Pelicula pelicula) {
		peliculasRepository.save(pelicula);
	}

	@Override
	public List<String> buscarGeneros() {
		//Esto lo recogeremos de la BD
		List<String> generos = new LinkedList<>();
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicos");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Romantica");
		generos.add("Ciencia Ficcion");
		return generos;
	}

	@Override
	public void eliminar(int idPelicula) {
		peliculasRepository.deleteById(idPelicula);
	}

}
