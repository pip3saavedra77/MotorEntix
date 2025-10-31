package MotorEntix.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {

	// 📌 Panel de administración principal
	@GetMapping("/panel")
	public String mostrarPanelAdmin(Model model) {
		// Aquí puedes agregar estadísticas o datos para el dashboard
		model.addAttribute("pagina", "dashboard");
		return "panel.admin";
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
		return "adminInventario";
	}

	@GetMapping("/proveedor")
	public String proveedor(Model model) {
		model.addAttribute("pagina", "proveedor");
		return "adminProveedor";
	}

	@GetMapping("/mi-perfil")
	public String miPerfil(Model model) {
		model.addAttribute("pagina", "mi-perfil");
		return "adminMiPerfil";
	}

	@GetMapping("/clientes")
	public String clientes(Model model) {
		model.addAttribute("pagina", "clientes");
		return "adminClientes";
	}

	@GetMapping("/reservas")
	public String reservas(Model model) {
		model.addAttribute("pagina", "reservas");
		return "adminReservas";
	}

	@GetMapping("/pagos")
	public String pagos(Model model) {
		model.addAttribute("pagina", "pagos");
		return "adminPagos";
	}

	@GetMapping("/configuracion")
	public String configuracion(Model model) {
		model.addAttribute("pagina", "configuracion");
		return "adminConfiguracion";
	}
}