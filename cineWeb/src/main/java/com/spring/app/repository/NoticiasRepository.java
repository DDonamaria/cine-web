package com.spring.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.model.Noticia;

/** Ponemos la anotacion @Repository para hacer un bean de la clase en el Root ApplicationContext
	pero realmente no es necesario porque como extender la interfaz JpaRepository Spring automaticamente 
	crea una instancia en el Root ApplicationContext.
*/
@Repository
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	
	//select * from Noticias where estatus = ?
	List<Noticia> findByEstatus(String estatus);
	
	//where fecha = ?
	List<Noticia> findByFecha(Date fecha);
	
	//where estatus = ? and Fecha = ?
	List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);
	
	//where estatus = ? or Fecha = ?
	//Si se da una condicion nos devuelve el registro
	List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);
	
	//where fecha between ? and ?
	List<Noticia> findByFechaBetween(Date fechaIni, Date fechaEnd);
	
	// select * from noticias where estatus = ? order by id desc limit 3
	public List<Noticia> findTop3ByEstatusOrderByIdDesc(String estatus);
}
