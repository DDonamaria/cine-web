package com.spring.app.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.app.model.Pelicula;
import com.spring.app.service.IPeliculasService;
import com.spring.app.utiles.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService servicePeliculas;

	@GetMapping("/index")
	public String mostrarPeliculas(Model model) {
		model.addAttribute("peliculas", servicePeliculas.buscarTodas());
		return "peliculas/listPeliculas";

	}

	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		model.addAttribute("generos", servicePeliculas.buscarGeneros());
		return "peliculas/formPelicula";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes, 
			@RequestParam("archivoImagen") MultipartFile multipart, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("Se han producido ERRORES en el DATA BINDING");
			for(ObjectError error : result.getAllErrors()) {
			System.out.println(error.getDefaultMessage());
			}
			return "peliculas/formPelicula";
		}
		
		if(!multipart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multipart, request);
			pelicula.setImagen(nombreImagen);
		}
		
		System.out.println(pelicula);
		servicePeliculas.crearPelicula(pelicula);
		attributes.addFlashAttribute("mensaje", "La pelicula se guardo correctamente");
		return "redirect:/peliculas/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}

}
