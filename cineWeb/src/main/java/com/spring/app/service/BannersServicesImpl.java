package com.spring.app.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.app.model.Banner;

@Service
public class BannersServicesImpl implements IBannersService {
	
	List<Banner> lista = null;
	
	public BannersServicesImpl() {
		
		System.out.println("Se crea una instancia de BannersServicesImpl");
			
		lista = new LinkedList<>();
		
		Banner banner1 = new Banner();
		banner1.setId(1);
		banner1.setTitulo("Banner #1");
		banner1.setArchivo("slide1.jpg");
		banner1.setFecha(new Date(01/04/2020));
		banner1.setEstatus("Activo");
		
		Banner banner2 = new Banner();
		banner2.setId(2);
		banner2.setTitulo("Banner #2");
		banner2.setArchivo("slide2.jpg");
		banner2.setFecha(new Date(02/04/2020));
		banner2.setEstatus("Activo");
		
		Banner banner3 = new Banner();
		banner3.setId(3);
		banner3.setTitulo("Banner #3");
		banner3.setArchivo("slide3.jpg");
		banner3.setFecha(new Date(03/04/2020));
		banner3.setEstatus("Activo");
		
		Banner banner4 = new Banner();
		banner4.setId(4);
		banner4.setTitulo("Banner #4");
		banner4.setArchivo("slide4.jpg");
		banner4.setFecha(new Date(04/04/2020));
		banner4.setEstatus("Activo");
		
		Banner banner5 = new Banner();
		banner5.setId(5);
		banner5.setTitulo("Banner #5");
		banner5.setArchivo("slide5.jpg");
		banner5.setFecha(new Date(05/04/2020));
		banner5.setEstatus("Activo");
		
		Banner banner6 = new Banner();
		banner6.setId(6);
		banner6.setTitulo("Banner #6");
		banner6.setArchivo("slide6.jpg");
		banner6.setFecha(new Date(06/04/2020));
		banner6.setEstatus("Activo");
		
		Banner banner7 = new Banner();
		banner7.setId(7);
		banner7.setTitulo("Banner #7");
		banner7.setArchivo("slide7.jpg");
		banner7.setFecha(new Date(07/04/2020));
		banner7.setEstatus("Activo");
		
		lista.add(banner1);
		lista.add(banner2);
		lista.add(banner3);
		lista.add(banner4);
		lista.add(banner5);
		lista.add(banner6);
		lista.add(banner7);
		
	}
	
	@Override
	public List<Banner> buscarTodos() {
		return lista;
	}
	
	@Override
	public List<Banner> buscarTodosActivos() {
		List<Banner> listaActivos = lista.stream().filter(b -> b.getEstatus() == "Activo").collect(Collectors.toList());
		return listaActivos;
	}

	@Override
	public void crearBanner(Banner banner) {
		lista.add(banner);
	}
	

}
