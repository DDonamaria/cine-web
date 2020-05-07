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
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@GetMapping(value = "/")
	public String goHome(Model model) {
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> listaPeliculas = servicePeliculas.buscarPorFecha(new Date());

		model.addAttribute("fechas", listaFechas);
		// Añadimos la fecha actual ya que sera la fecha de busqueda por defecto
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", listaPeliculas);

		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar(@RequestParam("fecha") Date fecha, Model model) {
		// Control posibles errores durante el parseo de la fecha
		try { 
			//Metodo para obtener X dias siguientes
			List<String> listaFechas = Utileria.getNextDays(4);

			// Formateamos la fecha al formato dd-MM-yyyy
			String fechaFormateada = dateFormat.format(fecha);
			// Pasamos la fecha formateada de String a Date
			Date fechaFormaString = dateFormat.parse(fechaFormateada);
			
			List<Pelicula> lista = servicePeliculas.buscarPorFecha(fechaFormaString);

			model.addAttribute("fechas", listaFechas);
			model.addAttribute("fechaBusqueda", fechaFormateada);
			model.addAttribute("peliculas", lista);
			
		} catch (Exception e) {
			System.out.println("Error: HomeController.buscar() " + e.getMessage());
		}
		
		return "home";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") Date fecha) {

		List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));

		return "detail";
	}

	@RequestMapping(value = "/about")
	public String mostrarAcerca() {
		return "acerca";
	}

	@RequestMapping(value = "/formLogin", method = RequestMethod.GET)
	public String mostrarLogin() {
		return "formLogin";
	}

	@ModelAttribute("banners")
	public List<Banner> getBannersActivos() {
		return serviceBanners.buscarActivos();
	}

	@ModelAttribute("noticias")
	public List<Noticia> getUltimasNoticias() {
		return serviceNoticias.buscarUltimas();
	}

	/**
	 * Metodo para personalizar el formato de fecha en el Data Binding
	 * 
	 * @param binder
	 */
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		//Creamos el nuevo CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    //Registramos el CustomDateEditor como CustomEditor en el tipo de dato "Date"
	    binder.registerCustomEditor(Date.class, editor);
	}

}
