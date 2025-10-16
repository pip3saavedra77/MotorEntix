package MotorEntix.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class panelClienteController {

	@GetMapping("/vehiculos-cliente")
	public String mostrarVehiculosCliente(Model model) {
		// Puedes agregar datos al modelo si es necesario
		// model.addAttribute("vehiculos", vehiculoService.obtenerVehiculos());
		return "vehiculosCliente";
	}
	@GetMapping("/reservasCliente")
	public String mostrarreservas(Model model) {
		// Puedes agregar datos al modelo si es necesario
		// model.addAttribute("vehiculos", vehiculoService.obtenerVehiculos());
		return "reservas";
	}
	
	@GetMapping("/historial")
	public String historial(Model model) {
		// Puedes agregar datos al modelo si es necesario
		// model.addAttribute("vehiculos", vehiculoService.obtenerVehiculos());
		return "historial";
	}
	@GetMapping("/servicios")
	public String servicios(Model model) {
		// Puedes agregar datos al modelo si es necesario
		// model.addAttribute("vehiculos", vehiculoService.obtenerVehiculos());
		return "servicios";
	}
}
