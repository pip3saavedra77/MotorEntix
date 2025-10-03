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
import org.springframework.web.bind.annotation.RequestParam;

import MotorEntix.model.Vehiculo;
import MotorEntix.service.IVehiculoService;

@Controller
@RequestMapping("/admin")
public class panelAdminController {

	@Autowired
	private IVehiculoService vehiculoService;

	// 📌 ÚNICO método para mostrar el panel principal con búsqueda opcional
	@GetMapping("/panel")
	public String mostrarPanelAdmin(@RequestParam(value = "search", required = false) String search, Model model) {

		List<Vehiculo> vehiculos;

		if (search != null && !search.trim().isEmpty()) {
			// Si hay término de búsqueda, filtrar vehículos
			vehiculos = vehiculoService.buscarPorTermino(search);
		} else {
			// Si no hay búsqueda, mostrar todos
			vehiculos = vehiculoService.listarTodos();
		}

		model.addAttribute("vehiculos", vehiculos);
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("searchTerm", search);
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
		return "editarVehiculo";
	}

	// 📌 Guardar cambios del vehículo editado
	@PostMapping("/vehiculos/editar/{id}")
	public String actualizarVehiculo(@PathVariable Integer id, @ModelAttribute("vehiculo") Vehiculo vehiculo) {
		Vehiculo vehiculoExistente = vehiculoService.obtenerPorId(id);

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
		return "redirect:/admin/panel";
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
		return "adminInventario";
	}

	@GetMapping("/reportes")
	public String reportes() {
		return "adminReportes";
	}

	@GetMapping("/reservas")
	public String reservas() {
		return "adminReservas";
	}

	@GetMapping("/servicios")
	public String servicios() {
		return "adminServicios";
	}

	@GetMapping("/trabajadores")
	public String trabajadores() {
		return "adminTrabajadores";
	}
}