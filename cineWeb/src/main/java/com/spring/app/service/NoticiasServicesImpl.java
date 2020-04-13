package com.spring.app.service;

import org.springframework.stereotype.Service;

import com.spring.app.model.Noticia;

@Service
public class NoticiasServicesImpl implements INoticiasService{

	// Constructor vacio. Unicamente para imprimir un mensaje al crearse una instancia
	public NoticiasServicesImpl() {
		System.out.println("Creando una instancia de la clase NoticiasServicesImpl");
	}	
	
	@Override
	public void guardar(Noticia noticia) {
		System.out.println("Se ha guardado la noticia con titulo: " + noticia.getTitulo());
	}
	
	

}
