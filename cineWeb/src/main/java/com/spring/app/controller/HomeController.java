package com.spring.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.app.model.Banner;
import com.spring.app.model.Horario;
import com.spring.app.model.Noticia;
import com.spring.app.model.Pelicula;
import com.spring.app.service.IBannersService;
import com.spring.app.service.IHorariosService;
import com.spring.app.service.INoticiasService;
import com.spring.app.service.IPeliculasService;
import com.spring.app.utiles.Utileria;

@Controller
public class HomeController {
	
	// Inyectamos las instancia desde el Root ApplicationContext
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IBannersService serviceBanners;
	
	@Autowired
	private IHorariosService serviceHorarios;
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	// Formateador de Fecha
	private SimpleDateFormat dateformater = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * Metodo para mostrar la pagina principal
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/")
	public String goHome(Model model) {
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> listaPeliculas = servicePeliculas.buscarPorFecha(new Date());
		
		model.addAttribute("fechas", listaFechas);
		// Añadimos la fecha actual ya que sera la fecha de busqueda por defecto
		model.addAttribute("fechaBusqueda", dateformater.format(new Date()));
		model.addAttribute("peliculas", listaPeliculas);
		
		return "home";
	}
	
	/**
	 * Metodo para filtrar peliculas por fecha en la pagina principal
	 * @param fecha
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar(@RequestParam("fecha") Date fecha, Model model) {
		try { //Controlamos posibles errores durante el parseo de la fecha
			List<String> listaFechas = Utileria.getNextDays(4);
			
			//Formateamos la fecha al formato dd-MM-yyyy
			String fechaFormateada = dateformater.format(fecha);
			//Pasamos la fecha formateada de String a Date
			Date fechaFormaString = dateformater.parse(fechaFormateada);
			List<Pelicula> lista = servicePeliculas.buscarPorFecha(fechaFormaString);
			
			model.addAttribute("fechas", listaFechas);
			model.addAttribute("fechaBusqueda", fechaFormateada);
			model.addAttribute("peliculas", lista);
		} catch (Exception e) {
			System.out.println("Error: HomeController.buscar() " + e.getMessage());
		}
		return "home";
	}

	/**
	 * Metodo para mostrar detalles de la pelicula seleccionada 
	 * @param model
	 * @param idPelicula
	 * @param fecha
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula,
			@RequestParam("fecha") Date fecha) {
		
		List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateformater.format(fecha));
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));

		return "detail";
	}
	
	/**
	 * Metodo para direccionar a la vista de Acerca de Nosotros
	 * @return
	 */
	@RequestMapping(value = "/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	/**
	 * Metodo para obtener los Banners activos y añadirlos al MODELO
	 * @return
	 */
	@ModelAttribute("banners")
	public List<Banner> getBannersActivos(){
		return serviceBanners.buscarActivos();
	}
	
	/**
	 * Metodo para obtener las ultimas 3 Noticias y añadirlas al MODELO
	 * @return
	 */
	@ModelAttribute("noticias")
	public List<Noticia> getUltimasNoticias(){
		return serviceNoticias.buscarUltimas();
	}
	
	/**
	 * Metodo para personaliza el Data Binding 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateformater, false));
	}
	
}
