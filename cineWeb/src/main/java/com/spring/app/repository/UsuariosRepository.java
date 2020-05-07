package com.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.app.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
