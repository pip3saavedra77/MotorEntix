	package MotorEntix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import MotorEntix.model.Usuario;
import MotorEntix.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/panel")
	public String mostrarPanelAdmin(Model model, HttpSession session) {
		String rol = (String) session.getAttribute("rol");
		if (rol == null) {
			return "redirect:/login";
		}
		switch (rol.toLowerCase()) {
		case "administrador":
			break; // permitido
		case "cliente":
			return "redirect:/panel.cliente";
		case "trabajador":
			return "redirect:/panel.trabajador";
		case "dueno":
			return "redirect:/panel.dueno";
		default:
			return "redirect:/login";
		}
		agregarUsuarioAlModelo(model, session);
		model.addAttribute("pagina", "dashboard");
		return "administrador/panel.admin";
	}

	// 📌 Redirección a la vista de vehículos
	@GetMapping("/vehiculos")
	public String mostrarVehiculos() {
		return "redirect:/admin/vehiculos/lista";
	}

	// 📌 Otras secciones del panel
	@GetMapping("/inventario")
	public String inventario(Model model) {
		model.addAttribute("pagina", "inventario");
		return "administrador/inventario"; // ← Cuando lo crees
	}

	@GetMapping("/proveedor")
	public String proveedor(Model model) {
		model.addAttribute("pagina", "proveedor");
		return "administrador/proveedor"; // ← Cuando lo crees
	}

	@GetMapping("/mi-perfil")
	public String miPerfil(Model model, HttpSession session) {
		agregarUsuarioAlModelo(model, session);
		model.addAttribute("pagina", "mi-perfil");
		return "administrador/mi-perfil";
	}

	@GetMapping("/clientes")
	public String clientes(Model model) {
		model.addAttribute("pagina", "clientes");
		return "administrador/clientes"; // ← Cuando lo crees
	}

	@GetMapping("/reservas")
	public String reservas(Model model) {
		model.addAttribute("pagina", "reservas");
		return "administrador/reservas"; // ← Cuando lo crees
	}

	@GetMapping("/pagos")
	public String pagos(Model model) {
		model.addAttribute("pagina", "pagos");
		return "administrador/pagos"; // ← Cuando lo crees
	}

	@GetMapping("/configuracion")
	public String configuracion(Model model) {
		model.addAttribute("pagina", "configuracion");
		return "administrador/configuracion"; // ← Cuando lo crees
	}

	private void agregarUsuarioAlModelo(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		String usuarioEmail = (String) session.getAttribute("usuarioEmail");
		Usuario usuario = null;
		if (usuarioId != null) {
			usuario = usuarioService.findById(usuarioId);
		}
		if (usuario == null && usuarioEmail != null) {
			usuario = usuarioService.findByCorreo(usuarioEmail);
		}
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
		}
	}
}