	package MotorEntix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {

	// 📌 Panel de administración principal - USA EL NOMBRE REAL
	@GetMapping("/panel")
	public String mostrarPanelAdmin(Model model) {
		model.addAttribute("pagina", "dashboard");
		return "administrador/panel.admin"; // ← NOMBRE REAL
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
	public String miPerfil(Model model) {
		model.addAttribute("pagina", "mi-perfil");
		return "administrador/mi-perfil"; // ← Cuando lo crees
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
}