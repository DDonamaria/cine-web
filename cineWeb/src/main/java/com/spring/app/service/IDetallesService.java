package com.spring.app.service;

import com.spring.app.model.Detalle;

public interface IDetallesService {
	
	void guardar(Detalle detalle);
	void eliminar(int idDetalle);

}
