package com.spring.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/index")
	public String mostrarBanner(Model model) {
		List<Banner> banners = serviceBanners.buscarTodos();
		model.addAttribute("banners", banners);
		return "banners/listBanners";
	}

	@GetMapping("/create")
	public String crear() {
		return "banners/formBanner";
	}

	@PostMapping("/save")
	public String guardar(Banner banner, BindingResult result, @RequestParam("archivoImagen") MultipartFile multipart,
			HttpServletRequest request, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			System.out.println("Se han producido ERRORES en el DATA BINDING");
			return "banners/formBanner";
		}
		
		if(!multipart.isEmpty()) {
			banner.setArchivo(Utileria.guardarImagen(multipart, request));
		}
		
		serviceBanners.crearBanner(banner);
		attributes.addFlashAttribute("mensaje", "El banner se guardo correctamente");
		return "redirect:/banners/index";
	}

}
