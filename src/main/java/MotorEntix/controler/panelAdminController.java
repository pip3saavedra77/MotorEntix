package MotorEntix.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class panelAdminController {

	@GetMapping("/panel")
	public String mostrarPanelAdmin() {
		// carpeta.panel/admin
		return "panel.admin/panel.admin";
	}

	@GetMapping("/inventario")
	public String inventario() {
		return "panel.admin/adminInventario";
		// → busca en templates/panel.admin/adminInventario.html
	}

	@GetMapping("/reportes")
	public String reportes() {
		return "panel.admin/adminReportes";
	}

	@GetMapping("/reservas")
	public String reservas() {
		return "panel.admin/adminReservas";
	}

	@GetMapping("/servicios")
	public String servicios() {
		return "panel.admin/adminServicios";
	}

	@GetMapping("/trabajadores")
	public String trabajadores() {
		return "panel.admin/adminTrabajadores";
	}
}
