package com.spring.app.service;

import java.util.List;

import com.spring.app.model.Usuario;

public interface IUsuariosService {
	List<Usuario> buscarTodos();
	Usuario buscarPorId(int id);
	void guardar(Usuario usuario);
	void eliminar(int idUsuario);
}
