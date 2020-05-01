package com.spring.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.app.model.Noticia;
import com.spring.app.repository.NoticiasRepository;

@Service
public class NoticiasServicesJPA implements INoticiasService {

	@Autowired
	private NoticiasRepository noticiasRepository;

	@Override
	public List<Noticia> buscarTodas() {
		noticiasRepository.findAll();
		return null;
	}
	
	@Override
	public Page<Noticia> buscarTodas(Pageable page) {
		return noticiasRepository.findAll(PageRequest.of(page.getPageNumber(),7, Sort.by("id").descending()));
//		return noticiasRepository.findAll(page);
		
	}
	
	@Override
	public List<Noticia> buscarUltimas() {
		List<Noticia> noticias = noticiasRepository.findTop3ByEstatusOrderByIdDesc("Activa");		
		return noticias;
	}

	@Override
	public Noticia buscarPorId(int idNoticia) {
		Optional<Noticia> optional = noticiasRepository.findById(idNoticia);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}
	
	@Override
	public void guardar(Noticia noticia) {
		noticiasRepository.save(noticia);
	}

	@Override
	public void eliminar(int idNoticia) {
		noticiasRepository.deleteById(idNoticia);
	}

	
	
	
	

}
