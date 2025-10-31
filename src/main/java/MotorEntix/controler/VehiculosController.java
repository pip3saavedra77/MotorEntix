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
@RequestMapping("/admin/vehiculos")
public class VehiculosController {

	@Autowired
	private IVehiculoService vehiculoService;

	// 📌 Lista de vehículos con búsqueda
	@GetMapping("/lista")
	public String listarVehiculos(@RequestParam(required = false) String search, Model model) {
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
		model.addAttribute("pagina", "vehiculos");
		return "vehiculos";
	}

	// 📌 Mostrar formulario de registro de vehículos
	@GetMapping("/registrar")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("pagina", "vehiculos");
		return "registrarVehiculo";
	}

	// 📌 Guardar vehículo (crear o editar)
	@PostMapping("/guardar")
	public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
		vehiculoService.guardar(vehiculo);
		return "redirect:/admin/vehiculos/lista";
	}

	// 📌 Mostrar formulario de edición
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		Vehiculo vehiculo = vehiculoService.obtenerPorId(id);
		model.addAttribute("vehiculo", vehiculo);
		model.addAttribute("pagina", "vehiculos");
		return "editarVehiculo";
	}

	// 📌 Guardar cambios del vehículo editado
	@PostMapping("/editar/{id}")
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
		return "redirect:/admin/vehiculos/lista";
	}

	// 📌 Eliminar vehículo
	@PostMapping("/eliminar/{id}")
	public String eliminarVehiculo(@PathVariable Integer id) {
		vehiculoService.eliminar(id);
		return "redirect:/admin/vehiculos/lista";
	}
}