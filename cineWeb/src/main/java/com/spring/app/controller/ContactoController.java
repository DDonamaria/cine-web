package com.spring.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.app.model.Contacto;
import com.spring.app.service.IPeliculasService;

@Controller
public class ContactoController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	/**
	 * Metodo para direccionar al formuario de contacto
	 * @param contacto
	 * @param model
	 * @return
	 */
	@GetMapping("/contacto")
	public String mostrarFormulario(@ModelAttribute("contacto") Contacto contacto, Model model) {
		model.addAttribute("generos", servicePeliculas.buscarGeneros());
		model.addAttribute("tiposNotificaciones", tipoNotificaciones());
		return "formContacto";
	}
	
	/**
	 * Metodo para crear/editar un mensaje
	 * @param contacto
	 * @param model
	 * @return
	 */
	@PostMapping("/contacto")
	public String guardar(@ModelAttribute("contacto") Contacto contacto) {
		System.out.println(contacto);
		return "redirect:/contacto";
	}
	
	/**
	 * Metodo para obtener los Tipos de Notificaciones
	 * @return
	 */
	private List<String> tipoNotificaciones(){
		List<String> tipos = new LinkedList<>();
		tipos.add("Estrenos");
		tipos.add("Promociones");
		tipos.add("Noticias");
		tipos.add("Preventas");
		
		return tipos;
	}

}
