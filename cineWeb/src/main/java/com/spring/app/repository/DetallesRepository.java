package com.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.model.Detalle;

/** Ponemos la anotacion @Repository para hacer un bean de la clase en el Root ApplicationContext
	pero realmente no es necesario porque como extender la interfaz JpaRepository Spring automaticamente 
	crea una instancia en el Root ApplicationContext.
*/
@Repository
public interface DetallesRepository extends JpaRepository<Detalle, Integer> {

}
