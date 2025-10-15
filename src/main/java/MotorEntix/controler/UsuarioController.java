package MotorEntix.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import MotorEntix.model.Usuario;
import MotorEntix.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/registroUsuarios")
	public String mostrarRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registroUsuarios";
	}

	@PostMapping("/registrar")
	public String registrarUsuario(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
		try {
			// Validar campos obligatorios
			if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty() || usuario.getApellido() == null
					|| usuario.getApellido().trim().isEmpty() || usuario.getCorreo() == null
					|| usuario.getCorreo().trim().isEmpty() || usuario.getContrasena() == null
					|| usuario.getContrasena().trim().isEmpty()) {

				model.addAttribute("error", "Todos los campos marcados con * son obligatorios");
				return "registroUsuarios";
			}

			// Registrar el usuario
			Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

			// Mensaje de éxito
			session.setAttribute("mensaje", "Usuario registrado exitosamente. Ahora puedes iniciar sesión.");
			return "redirect:/login";

		} catch (Exception e) {
			model.addAttribute("error", "Error al registrar usuario: " + e.getMessage());
			return "registroUsuarios";
		}
	}

	// ELIMINAR ESTE MÉTODO - Ya existe en LoginController
	// @GetMapping("/login")
	// public String login(HttpSession session, Model model) {
	// // Limpiar mensaje de sesión después de mostrarlo
	// String mensaje = (String) session.getAttribute("mensaje");
	// if (mensaje != null) {
	// model.addAttribute("mensaje", mensaje);
	// session.removeAttribute("mensaje");
	// }
	// return "login";
	// }
}