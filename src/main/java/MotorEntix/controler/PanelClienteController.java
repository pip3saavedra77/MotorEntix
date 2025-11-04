package MotorEntix.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import MotorEntix.model.Usuario;
import MotorEntix.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;

@Controller
public class PanelClienteController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/panel.cliente")
	public String mostrarPanelCliente(Model model, HttpSession session) {
		// DEBUG EXTENDIDO
		System.out.println("=== DEBUG SESIÓN COMPLETO ===");
		System.out.println("usuarioId: " + session.getAttribute("usuarioId"));
		System.out.println("usuarioEmail: " + session.getAttribute("usuarioEmail"));
		System.out.println("usuarioLogueado: " + session.getAttribute("usuarioLogueado"));
		System.out.println("rol: " + session.getAttribute("rol"));

		// Listar todos los atributos de sesión
		java.util.Enumeration<String> attributeNames = session.getAttributeNames();
		System.out.println("Todos los atributos de sesión:");
		while (attributeNames.hasMoreElements()) {
			String name = attributeNames.nextElement();
			System.out.println(" - " + name + ": " + session.getAttribute(name));
		}

		// Obtener el ID del usuario de la sesión
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		String usuarioEmail = (String) session.getAttribute("usuarioEmail");

		Usuario usuario = null;

		// PRIMERO: Buscar por ID
		if (usuarioId != null) {
			System.out.println("Buscando usuario por ID: " + usuarioId);
			usuario = usuarioService.findById(usuarioId);
			System.out.println("Resultado búsqueda por ID: " + (usuario != null ? usuario.getNombre() : "NULL"));
		}

		// SEGUNDO: Si no hay por ID, buscar por email
		if (usuario == null && usuarioEmail != null) {
			System.out.println("Buscando usuario por email: " + usuarioEmail);
			usuario = usuarioService.findByCorreo(usuarioEmail);
			System.out.println("Resultado búsqueda por email: " + (usuario != null ? usuario.getNombre() : "NULL"));
		}

		if (usuario != null) {
			model.addAttribute("usuario", usuario);
			System.out.println("✅ Usuario cargado en modelo: " + usuario.getNombre() + " " + usuario.getApellido());
			System.out.println("✅ Email: " + usuario.getCorreo());
			System.out.println("✅ Teléfono: " + usuario.getTelefono());
		} else {
			System.out.println("❌ NO se pudo cargar usuario para el perfil");
			// NO cargamos usuario de prueba para evitar posibles errores
		}

		return "clientes/panel.cliente";
	}

	@GetMapping("/vehiculos-cliente")
	public String mostrarVehiculosCliente(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			model.addAttribute("usuario", usuario);
		}
		return "clientes/vehiculos";
	}

	@GetMapping("/reservasCliente")
	public String mostrarReservas(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			model.addAttribute("usuario", usuario);
		}
		return "clientes/reservas";
	}

	@GetMapping("/historial")
	public String historial(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			model.addAttribute("usuario", usuario);
		}
		return "clientes/historial";
	}

	@GetMapping("/servicios")
	public String servicios(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			model.addAttribute("usuario", usuario);
		}
		return "clientes/servicios";
	}
}