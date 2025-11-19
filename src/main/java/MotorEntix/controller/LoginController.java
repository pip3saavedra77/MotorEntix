package MotorEntix.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import MotorEntix.model.Usuario;
import MotorEntix.service.IUsuarioService;
import java.util.Optional;

@Controller
public class LoginController {

	private final IUsuarioService usuarioService;

	public LoginController(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// Mostrar página de login CON manejo de mensajes
	@GetMapping("/login")
	public String mostrarLogin(HttpSession session, Model model) {
		// Limpiar mensaje de sesión después de mostrarlo
		String mensaje = (String) session.getAttribute("mensaje");
		if (mensaje != null) {
			model.addAttribute("mensaje", mensaje);
			session.removeAttribute("mensaje");
		}
		return "login"; // templates/login.html
	}

	@PostMapping("/procesar-login")
	public String procesarLogin(@RequestParam String usuario, @RequestParam String contrasena, HttpSession session,
			Model model) {

		Optional<Usuario> usuarioOpt = usuarioService.validarUsuario(usuario, contrasena);

		if (usuarioOpt.isPresent()) {
			Usuario u = usuarioOpt.get();

			// DEBUG: Ver qué datos tenemos del usuario
			System.out.println("=== LOGIN DEBUG ===");
			System.out.println("ID: " + u.getId_usuario());
			System.out.println("Email: " + u.getCorreo());
			System.out.println("Nombre: " + u.getNombre());
			System.out.println("Rol: " + u.getRol());

			// GUARDAR DATOS COMPLETOS EN SESIÓN - ESTO ES CLAVE
			session.setAttribute("usuarioId", u.getId_usuario()); // ← ID para buscar datos
			session.setAttribute("usuarioEmail", u.getCorreo()); // ← Email como backup
			session.setAttribute("usuarioLogueado", u.getNombre()); // ← Nombre para mostrar
			session.setAttribute("rol", u.getRol()); // ← Rol para redirección
			session.setAttribute("usuario", u); // ← OBJETO COMPLETO - ESTA LÍNEA FALTABA

			// DEBUG: Verificar qué se guardó en sesión
			System.out.println("=== SESIÓN GUARDADA ===");
			System.out.println("usuarioId: " + session.getAttribute("usuarioId"));
			System.out.println("usuarioEmail: " + session.getAttribute("usuarioEmail"));
			System.out.println("usuario (objeto): " + session.getAttribute("usuario"));

			// Redirección según rol en BD
			String rol = u.getRol() != null ? u.getRol().toLowerCase() : "";
			switch (rol) {
			case "administrador":
				return "redirect:/admin/panel";
			case "cliente":
				return "redirect:/panel.cliente";
			case "trabajador":
				return "redirect:/panel.trabajador";
			case "dueno":
				return "redirect:/panel.dueno";
			default:
				model.addAttribute("error", "Rol no válido");
				return "login";
			}
		} else {
			model.addAttribute("error", "Usuario o contraseña incorrectos");
			return "login";
		}
	}

	// Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index"; // templates/index.html
	}
}