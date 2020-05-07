package com.spring.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {

	/**
	 * Metodo para cerrar sesion del usuario logueado
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/logout")
	public String cerrarSesion(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/formLogin";
	}

}
