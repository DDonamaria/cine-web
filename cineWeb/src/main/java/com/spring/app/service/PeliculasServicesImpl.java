package com.spring.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.app.model.Pelicula;

@Service
public class PeliculasServicesImpl implements IPeliculasService{
	
	private List<Pelicula> lista = null;
	
	public PeliculasServicesImpl() {
		
		System.out.println("Creando una instancia de la clase PeliculasService");
		
		SimpleDateFormat dateformater = new SimpleDateFormat("dd-MM-yyyy");

		try {
			lista = new LinkedList<>();

			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("La bella y la bestia");
			pelicula1.setGenero("Aventura");
			pelicula1.setCategoria("A");
			pelicula1.setDuracion(120);
			pelicula1.setFechaEstreno(dateformater.parse("27-05-1992"));
			pelicula1.setImagen("bella.png");

			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("Kong");
			pelicula2.setGenero("Ficcion");
			pelicula2.setCategoria("A");
			pelicula2.setDuracion(120);
			pelicula2.setFechaEstreno(dateformater.parse("27-05-1992"));
			pelicula2.setImagen("kong.jpg");

			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Titanic");
			pelicula3.setGenero("Romantico");
			pelicula3.setCategoria("A");
			pelicula3.setDuracion(180);
			pelicula3.setFechaEstreno(dateformater.parse("27-05-1992"));
			pelicula3.setImagen("titanic.jpg");

			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("El Padrino");
			pelicula4.setGenero("Aventura");
			pelicula4.setCategoria("A");
			pelicula4.setDuracion(120);
			pelicula4.setFechaEstreno(dateformater.parse("27-05-1992"));
			pelicula4.setImagen("padrino.jpg");

			Pelicula pelicula5 = new Pelicula();
			pelicula5.setId(5);
			pelicula5.setTitulo("Ghost");
			pelicula5.setGenero("Romantica");
			pelicula5.setCategoria("A");
			pelicula5.setDuracion(120);
			pelicula5.setFechaEstreno(dateformater.parse("27-05-1992"));
			pelicula5.setImagen("ghost.jpg");

			Pelicula pelicula6 = new Pelicula();
			pelicula6.setId(6);
			pelicula6.setTitulo("Contratiempo");
			pelicula6.setGenero("Aventura");
			pelicula6.setCategoria("A");
			pelicula6.setDuracion(120);
			pelicula6.setFechaEstreno(dateformater.parse("27-05-1992"));
			pelicula6.setImagen("contratiempo.png");

			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			lista.add(pelicula5);
			lista.add(pelicula6);
			
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public List<Pelicula> buscarTodas() {
		return lista;
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		
		for(Pelicula p: lista) { //en "p" tendremos cada instancia de Pelicula de "lista"
			if(p.getId() == idPelicula) {
				return p;
			}
		}
		
		return null;
	}

	@Override
	public void crearPelicula(Pelicula pelicula) {
		lista.add(pelicula);
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
	

}
