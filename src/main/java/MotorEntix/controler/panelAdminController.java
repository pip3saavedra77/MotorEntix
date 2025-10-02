package MotorEntix.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import MotorEntix.model.Vehiculo;
import MotorEntix.service.IVehiculoService;

@Controller
@RequestMapping("/admin")
public class panelAdminController {

	@Autowired
	private IVehiculoService vehiculoService;

	// 📌 Mostrar el panel principal con la lista de vehículos
	@GetMapping("/panel")
	public String mostrarPanelAdmin(Model model) {
		List<Vehiculo> vehiculos = vehiculoService.listarTodos();
		model.addAttribute("vehiculos", vehiculos);
		model.addAttribute("vehiculo", new Vehiculo());
		return "panel.admin";
	}

	// 📌 Mostrar formulario de registro de vehículos
	@GetMapping("/registrar")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		return "registrarVehiculo";
	}

	// 📌 Guardar vehículo (crear o editar)
	@PostMapping("/vehiculos/guardar")
	public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
		vehiculoService.guardar(vehiculo);
		return "redirect:/admin/panel";
	}

	// 📌 Mostrar formulario de edición
	@GetMapping("/vehiculos/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		Vehiculo vehiculo = vehiculoService.obtenerPorId(id);
		model.addAttribute("vehiculo", vehiculo);
		return "editarVehiculo"; // Vista Thymeleaf
	}

	// 📌 Guardar cambios del vehículo editado
	@PostMapping("/vehiculos/editar/{id}")
	public String actualizarVehiculo(@PathVariable Integer id, @ModelAttribute("vehiculo") Vehiculo vehiculo) {
		Vehiculo vehiculoExistente = vehiculoService.obtenerPorId(id);

		// actualizar los datos
		vehiculoExistente.setMarca(vehiculo.getMarca());
		vehiculoExistente.setModelo(vehiculo.getModelo());
		vehiculoExistente.setPlaca(vehiculo.getPlaca());
		vehiculoExistente.setAnio(vehiculo.getAnio());
		vehiculoExistente.setColor(vehiculo.getColor());
		vehiculoExistente.setCombustible(vehiculo.getCombustible());
		vehiculoExistente.setDescripcion(vehiculo.getDescripcion());
		vehiculoExistente.setEstadoVehiculo(vehiculo.getEstadoVehiculo());
		vehiculoExistente.setTipoVehiculo(vehiculo.getTipoVehiculo());
		vehiculoExistente.setTransmicion(vehiculo.getTransmicion());

		vehiculoService.guardar(vehiculoExistente);

		return "redirect:/admin/panel"; // Redirige al panel después de editar
	}

	// 📌 Eliminar vehículo
	@PostMapping("/vehiculos/eliminar/{id}")
	public String eliminarVehiculo(@PathVariable Integer id) {
		vehiculoService.eliminar(id);
		return "redirect:/admin/panel";
	}

	// ================= Otras secciones =================
	@GetMapping("/inventario")
	public String inventario() {
		return "panel.admin/adminInventario";
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
