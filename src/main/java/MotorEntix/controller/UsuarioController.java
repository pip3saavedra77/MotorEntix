package MotorEntix.controller;

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

	@GetMapping("/editar-perfil")
	public String mostrarFormularioEdicion(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		String rol = (String) session.getAttribute("rol");
		model.addAttribute("rol", rol);

		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			if (usuario != null) {
				model.addAttribute("usuario", usuario);
				return "clientes/editarPerfil"; // ← Vista de edición de perfil compartida
			}
		}
		return "redirect:/panel.cliente";
	}

	@PostMapping("/actualizar-perfil")
	public String actualizarPerfil(@ModelAttribute Usuario usuarioActualizado, HttpSession session, Model model) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");

		if (usuarioId != null) {
			try {
				Usuario usuarioExistente = usuarioService.findById(usuarioId);

				if (usuarioExistente != null) {
					// Actualizar campos editables
					usuarioExistente.setNombre(usuarioActualizado.getNombre());
					usuarioExistente.setApellido(usuarioActualizado.getApellido());
					usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
					usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
					usuarioExistente.setCorreo(usuarioActualizado.getCorreo());

					// Guardar cambios
					usuarioService.actualizarUsuario(usuarioExistente);

					session.setAttribute("usuarioLogueado", usuarioExistente.getNombre());
					String rol = usuarioExistente.getRol() != null ? usuarioExistente.getRol().toLowerCase() : "";
					switch (rol) {
					case "administrador":
						return "redirect:/admin/mi-perfil?exito=true";
					case "cliente":
					default:
						return "redirect:/panel.cliente?exito=true";
					}
				}
			} catch (Exception e) {
				model.addAttribute("error", "Error al actualizar el perfil");
				return "clientes/editarPerfil"; // 	 Vista de edicin de perfil
			}
		}
		String rolSession = (String) session.getAttribute("rol");
		if (rolSession != null && rolSession.equalsIgnoreCase("administrador")) {
			return "redirect:/admin/mi-perfil";
		}
		return "redirect:/panel.cliente";
	}
}