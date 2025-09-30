package MotorEntix.controler;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import MotorEntix.model.Usuario;
import MotorEntix.service.IUsuarioService;


@Controller
public class UsuarioController {

	private final IUsuarioService IUsuarioService;

	private final IUsuarioService usuarioService;

	public UsuarioController(IUsuarioService usuarioService, IUsuarioService IUsuarioService) {
		this.usuarioService = usuarioService;
		this.IUsuarioService = IUsuarioService;
	}

	// Mostrar formulario de login
	@GetMapping("/login.html")
	public String mostrarLogin() {
		return "login"; // busca login.html en templates
	}
		

	// Procesar login
	@PostMapping("/procesar-login")
	public String procesarLogin(@RequestParam String usuario, // aquí puede ser correo o username
			@RequestParam String contrasena, @RequestParam String rol, HttpSession session, Model model) {

		// Validar contra la BD
		return IUsuarioService.validarUsuario(usuario, contrasena).map(u -> {
			if (!u.getRol().equalsIgnoreCase(rol)) {
				model.addAttribute("error", "El rol no coincide con el usuario.");
				return "login";
			}

			// Guardar en sesión
			session.setAttribute("usuarioLogueado", u.getNombre());
			session.setAttribute("rol", u.getRol());

			// Redirección según rol
			switch (u.getRol().toLowerCase()) {
			case "administrador":
				return "redirect:/panel.admin.html";
			case "cliente":
				return "redirect:/panel.cliente.html";
			case "dueno":
				return "redirect:/panelDueno.html";
			default:
				return "redirect:/index.html";
			}
		}).orElseGet(() -> {
			model.addAttribute("error", "Usuario o contraseña incorrectos");
			return "login";
		});
	}

	// Cerrar sesión
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.html";

	}
	
}
