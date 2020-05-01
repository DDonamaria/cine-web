package com.spring.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.app.model.Noticia;

public interface INoticiasService {
	List<Noticia> buscarTodas();
	Page<Noticia> buscarTodas(Pageable Page);
	List<Noticia> buscarUltimas();
	Noticia buscarPorId(int idNoticia);
	void guardar(Noticia noticia);
	void eliminar(int idNoticia);
	

}
