package com.spring.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.model.Usuario;
import com.spring.app.repository.UsuariosRepository;

@Service
public class UsuariosServicesJPA implements IUsuariosService{
	
	@Autowired
	private UsuariosRepository repository;

	@Override
	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}
	
	@Override
	public Usuario buscarPorId(int id) {
		Optional<Usuario> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public void guardar(Usuario usuario) {
		repository.save(usuario);
	}

	@Override
	public void eliminar(int idUsuario) {
		repository.deleteById(idUsuario);
	}

}
