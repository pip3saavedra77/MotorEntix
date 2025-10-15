package MotorEntix.controler;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import MotorEntix.service.IUsuarioService;

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

	// Procesar login
	@PostMapping("/procesar-login")
	public String procesarLogin(@RequestParam String usuario, @RequestParam String contrasena, @RequestParam String rol,
			HttpSession session, Model model) {

		return usuarioService.validarUsuario(usuario, contrasena).map(u -> {
			if (!u.getRol().equalsIgnoreCase(rol)) {
				model.addAttribute("error", "El rol no coincide con el usuario.");
				return "login";
			}

			// Guardar datos en sesión
			session.setAttribute("usuarioLogueado", u.getNombre());
			session.setAttribute("rol", u.getRol());

			// Redirección según rol
			switch (u.getRol().toLowerCase()) {
			case "administrador":
				return "redirect:/admin/panel";
			case "cliente":
				return "redirect:/panel.cliente";
			case "dueno":
				return "redirect:/panel.dueno";
			default:
				return "redirect:/index";
			}
		}).orElseGet(() -> {
			model.addAttribute("error", "Usuario o contraseña incorrectos");
			return "login";
		});
	}

	// Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	// Paneles
	@GetMapping("/panel.admin")
	public String panelAdmin() {
		return "panel.admin"; // templates/panel.admin.html
	}

	@GetMapping("/panel.cliente")
	public String panelCliente() {
		return "panel.cliente"; // templates/panel.cliente.html
	}

	@GetMapping("/panel.dueno")
	public String panelDueno() {
		return "panel.dueno"; // templates/panel.dueno.html
	}

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index"; // templates/index.html
	}
}