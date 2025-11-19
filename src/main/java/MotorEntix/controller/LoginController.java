package MotorEntix.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	// Mostrar página de login CON manejo de mensajes
	@GetMapping("/login")
	public String mostrarLogin(HttpSession session, Model model) {
		String mensaje = (String) session.getAttribute("mensaje");
		if (mensaje != null) {
			model.addAttribute("mensaje", mensaje);
			session.removeAttribute("mensaje");
		}
		return "login"; // templates/login.html
	}

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index"; // templates/index.html
	}
}