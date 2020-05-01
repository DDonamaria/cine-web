package com.spring.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.model.Pelicula;

/** Ponemos la anotacion @Repository para hacer un bean de la clase en el Root ApplicationContext
	pero realmente no es necesario porque como extender la interfaz JpaRepository Spring automaticamente 
	crea una instancia en el Root ApplicationContext.
*/
@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {
	
	public List<Pelicula> findByEstatus_OrderByTitulo(String estatus);

}
