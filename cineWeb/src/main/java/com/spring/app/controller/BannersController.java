package com.spring.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.app.model.Banner;
import com.spring.app.service.IBannersService;
import com.spring.app.utiles.Utileria;

@RequestMapping("/banners")
@Controller
public class BannersController {
	
	@Autowired
	private IBannersService serviceBanners;
	
	private String ImgBannerPrev;

	@GetMapping("/index")
	public String mostrarBanner(Model model) {
		List<Banner> banners = serviceBanners.buscarTodos();
		model.addAttribute("banners", banners);
		return "banners/listBanners";
	}
	
	@GetMapping("/indexPaginate")
	public String mostrarBannerPaginado(Model model, Pageable page) {
		Page<Banner> banners = serviceBanners.buscarTodos(page);
		model.addAttribute("banners", banners);
		return "banners/listBanners";
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Banner banner) {
		return "banners/formBanner";
	}

	@PostMapping("/save")
	public String guardar(Banner banner, BindingResult result, @RequestParam("archivoImagen") MultipartFile multipart,
			HttpServletRequest request, RedirectAttributes attributes) {
		
		//Conprobamos si hay errores en el mapeo
		if(result.hasErrors()) {
			System.out.println("Se han producido ERRORES en el DATA BINDING");
			return "banners/formBanner";
		}
		
		//Comprobamos si se ha cargado alguna imagen
		if(!multipart.isEmpty()) {
			banner.setArchivo(Utileria.guardarImagen(multipart, request));
		} else if(multipart.isEmpty() && !ImgBannerPrev.isEmpty()) {
			banner.setArchivo(ImgBannerPrev);
		} 
		ImgBannerPrev= null;
		
		serviceBanners.guardar(banner);
		attributes.addFlashAttribute("mensaje", "El banner se guardo correctamente");
		return "redirect:/banners/indexPaginate";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String editar(@PathVariable("id") int idBanner, Model model) {
		Banner banner = serviceBanners.buscarPorId(idBanner);
		ImgBannerPrev = banner.getArchivo();
		model.addAttribute("banner", banner);
		return "banners/formBanner";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
		serviceBanners.eliminar(idBanner);
		attributes.addFlashAttribute("mensaje", "El banner fue eliminado");
		return "redirect:/banners/index";

	}

}
