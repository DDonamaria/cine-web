package com.spring.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.app.model.Banner;
import com.spring.app.model.Pelicula;
import com.spring.app.service.IBannersService;
import com.spring.app.service.IPeliculasService;
import com.spring.app.utiles.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IBannersService serviceBanners;
	
	private SimpleDateFormat dateformater = new SimpleDateFormat("dd-MM-yyyy");

	@GetMapping(value = "/")
	public String goHome(Model model) {
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> listaPeliculas = servicePeliculas.buscarTodas();
		
		List<Banner> listaBanners = serviceBanners.buscarTodos();
		
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateformater.format(new Date()));
		model.addAttribute("peliculas", listaPeliculas);
		model.addAttribute("banners", listaBanners);
		return "home";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model model) {
		System.out.println("Buscando peliculas para fecha: " + fecha);
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", lista);
		
		return "home";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula,
			@RequestParam("fecha") String fecha) {
		
		System.out.println("A fecha " + fecha + " se mustra detalles de la pelicula " + idPelicula);

		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		System.out.println(servicePeliculas.buscarPorId(idPelicula));

		return "detail";
	}
	
}
