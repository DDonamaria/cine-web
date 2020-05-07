package com.spring.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.app.model.Noticia;
import com.spring.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	@Autowired
	private INoticiasService serviceNoticias;

	/**
	 * Metodo para mostrar la lista de Noticias
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/index")
	public String mostrarIndex(Model model) {
		List<Noticia> listaNoticias = serviceNoticias.buscarTodas();
		model.addAttribute("noticias", listaNoticias);
		return "noticias/listNoticias";
	}

	/**
	 * Metodo para mostrar la lista de los Noticias PAGINADAS
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Noticia> noticias = serviceNoticias.buscarTodas(page);
		model.addAttribute("noticias", noticias);
		return "noticias/listNoticias";
	}

	/**
	 * Metodo direccionar al formulario de creacion de Noticias
	 * 
	 * @param noticia
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Noticia noticia) {
		return "noticias/formNoticia";
	}

	/**
	 * Metodo para crear/editar una Noticia
	 * 
	 * @param noticia
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Noticia noticia, BindingResult result, Model model,
			RedirectAttributes attributes) {
		// Insertamos la noticia
		serviceNoticias.guardar(noticia);
		attributes.addFlashAttribute("mensaje", "La noticia se creo correctamente!");
		return "redirect:/noticias/indexPaginate";
	}

	/**
	 * Metodo para mostrar el formulario de edicion con los datos de la Noticia
	 * seleccionado
	 * 
	 * @param idNoticia
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/edit/{id}")
	public String editar(@PathVariable("id") int idNoticia, Model model) {
		Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
		model.addAttribute("noticia", noticia);
		return "noticias/formNoticia";
	}

	/**
	 * Metodo para eliminar el registro de la Noticia seleccionada
	 * 
	 * @param idNoticia
	 * @param model
	 * @param attributes
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
		serviceNoticias.eliminar(idNoticia);
		attributes.addFlashAttribute("mensaje", "La noticia fue eliminada!.");
		return "redirect:/noticias/indexPaginate";
	}
}
