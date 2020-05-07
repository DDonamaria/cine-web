package com.spring.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.app.model.Perfil;
import com.spring.app.model.Usuario;
import com.spring.app.service.IPerfilesService;
import com.spring.app.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IUsuariosService serviceUsuarios;
	
	@Autowired
	private IPerfilesService servicePerfiles;
	
	//Cargamos el bean de enciptacion configurado en springSecurity.xml
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/index")
	public String mostrarUsuarios(Model model) {
		List<Usuario> usuarios = serviceUsuarios.buscarTodos();
		model.addAttribute("usuarios", usuarios);
		System.out.println();
		return "usuarios/listUsuarios";
	}
	
	@GetMapping("/create")
	public String registrarUsuario(@ModelAttribute Usuario usuario) {
		return "formJoinIn";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Usuario usuario,@RequestParam("tipoPerfil") String tipoPerfil, RedirectAttributes attributes) {
		
		String pwdEncriptado = encoder.encode(usuario.getPassword());
		usuario.setPassword(pwdEncriptado);
		serviceUsuarios.guardar(usuario);
		
		Perfil perfil = new Perfil();
		perfil.setUsername(usuario.getUsername());
		perfil.setPerfil(tipoPerfil);
		servicePerfiles.guardar(perfil);
		
		attributes.addFlashAttribute("mensaje", "El usuario se creo correctamente");
		return "redirect:/joinin";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		serviceUsuarios.eliminar(idUsuario);
		attributes.addFlashAttribute("mensaje", "El usuario fue eliminado.");
		return "redirect:/usuarios/index";
	}
	
}
