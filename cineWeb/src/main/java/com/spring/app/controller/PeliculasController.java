package com.spring.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.app.model.Pelicula;
import com.spring.app.service.IDetallesService;
import com.spring.app.service.IPeliculasService;
import com.spring.app.utiles.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

	@Autowired
	private IPeliculasService servicePeliculas;

	@Autowired
	private IDetallesService serviceDetalles;

	/**
	 * Metodo para mostrar la lista de Peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarPeliculas(Model model) {
		model.addAttribute("peliculas", servicePeliculas.buscarTodas());
		return "peliculas/listPeliculas";

	}

	/**
	 * Metodo para mostrar la lista de Peliculas PAGINADAS
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pelicula> lista = servicePeliculas.buscarTodas(page);
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}

	/**
	 * Metodo direccionar al formulario de creacion de Peliculas
	 * 
	 * @param pelicula
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		return "peliculas/formPelicula";
	}

	/**
	 * Metodo para crear/editar una Pelicula
	 * 
	 * @param pelicula
	 * @param result
	 * @param attributes
	 * @param multipart
	 * @param request
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multipart, HttpServletRequest request) {

		// Comprobamos si ha habido errores durante el data binding
		if (result.hasErrors()) {
			System.out.println("Se han producido ERRORES en el DATA BINDING");

			for (ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			return "peliculas/formPelicula";
		}

		// Comprobamos si se ha cargado alguna imagen
		if (!multipart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multipart, request);
			pelicula.setImagen(nombreImagen);
		}

		// Guardamos el detalle
		serviceDetalles.guardar(pelicula.getDetalle());
		// Guardamos la pelicula
		servicePeliculas.guardar(pelicula);
		attributes.addFlashAttribute("mensaje", "La pelicula se guardo correctamente");
		return "redirect:/peliculas/indexPaginate";
	}

	/**
	 * Metodo para mostrar el formulario de edicion con los datos de la Pelicula
	 * seleccionado
	 * 
	 * @param idPelicula
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model) {
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		return "peliculas/formPelicula";

	}

	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		// Eliminamos la pelicula lo primero
		servicePeliculas.eliminar(idPelicula);
		// Seguido eliminamos los detalles
		serviceDetalles.eliminar(pelicula.getDetalle().getId());
		attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada");
		return "redirect:/peliculas/index";

	}

	/**
	 * Agregamos al modelo, el listado de Generos para que este disponible para
	 * todos los metodos de este controlador y la vista a la que direccionan
	 * 
	 * @return
	 */
	@ModelAttribute("generos")
	public List<String> getGeneros() {
		return servicePeliculas.buscarGeneros();
	}

	/**
	 * Metodo para personalizar el formato de fecha en el Data Binding
	 * 
	 * @param binder
	 */
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		//Creamos el nuevo CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    //Registramos el CustomDateEditor como CustomEditor en el tipo de dato "Date"
	    binder.registerCustomEditor(Date.class, editor);
	}

}
