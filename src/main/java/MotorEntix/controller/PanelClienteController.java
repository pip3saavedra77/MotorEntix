package MotorEntix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import MotorEntix.model.Reserva;
import MotorEntix.model.Servicio;
import MotorEntix.model.Usuario;
import MotorEntix.model.Trabajador;

import MotorEntix.service.IReservaService;
import MotorEntix.service.IServicioService;
import MotorEntix.service.IUsuarioService;
import MotorEntix.service.ITrabajadorService;
import MotorEntix.service.IVehiculoClienteService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PanelClienteController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private ITrabajadorService trabajadorService;

	@Autowired
	private IVehiculoClienteService vehiculoClienteService;

	@GetMapping("/panel.cliente")
	public String mostrarPanelCliente(Model model, HttpSession session) {
		// Validar rol
		String rol = (String) session.getAttribute("rol");
		if (rol == null) {
			return "redirect:/login";
		}
		switch (rol.toLowerCase()) {
		case "cliente":
			break; // permitido
		case "administrador":
			return "redirect:/admin/panel";
		case "trabajador":
			return "redirect:/panel.trabajador";
		case "dueno":
			return "redirect:/panel.dueno";
		default:
			return "redirect:/login";
		}
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
	public String mostrarVehiculosCliente(HttpSession session) {
		// Esta ruta solo redirige a /mis-vehiculos, que es manejada por VehiculoClienteController
		return "redirect:/mis-vehiculos";
	}

	@GetMapping("/reservasCliente")
	public String mostrarReservas(@RequestParam(name = "q", required = false) String q,
			@RequestParam(name = "estado", required = false) String estado,
			@RequestParam(name = "fecha", required = false) String fecha,
			Model model, HttpSession session) {

		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId == null) {
			return "redirect:/login";
		}

		Usuario usuario = usuarioService.findById(usuarioId);
		model.addAttribute("usuario", usuario);

		List<Reserva> reservas = reservaService.findByUsuarioId(usuarioId);
		
		// Filtro por texto (servicio, vehículo, ID)
		if (q != null && !q.trim().isEmpty()) {
			String query = q.trim().toLowerCase();
			reservas = reservas.stream().filter(r -> {
				boolean match = false;
				if (r.getServicio() != null && r.getServicio().getNombre() != null) {
					match |= r.getServicio().getNombre().toLowerCase().contains(query);
				}
				if (r.getVehiculo() != null) {
					String placa = r.getVehiculo().getPlaca() != null ? r.getVehiculo().getPlaca().toLowerCase() : "";
					String descVeh = (r.getVehiculo().getMarca() + " " + r.getVehiculo().getModelo()).toLowerCase();
					match |= placa.contains(query) || descVeh.contains(query);
				}
				// ID de reserva como texto (#RES123)
				if (r.getId() != null) {
					String idStr = ("res" + r.getId()).toLowerCase();
					match |= idStr.contains(query);
				}
				return match;
			}).toList();
			model.addAttribute("q", q.trim());
		}

		// Filtro por estado
		if (estado != null && !estado.trim().isEmpty()) {
			String estadoFiltro = estado.trim().toLowerCase();
			reservas = reservas.stream()
					.filter(r -> r.getEstado() != null && r.getEstado().toLowerCase().equals(estadoFiltro))
					.toList();
			model.addAttribute("estadoSeleccionado", estado.trim());
		}

		// Filtro por fecha exacta (si se envía)
		if (fecha != null && !fecha.trim().isEmpty()) {
			String fechaFiltro = fecha.trim();
			reservas = reservas.stream()
					.filter(r -> fechaFiltro.equals(r.getFechaReserva()))
					.toList();
			model.addAttribute("fechaSeleccionada", fechaFiltro);
		}

		model.addAttribute("reservas", reservas);

		model.addAttribute("servicios", servicioService.findAll());
		model.addAttribute("trabajadores", trabajadorService.findAll());
		model.addAttribute("vehiculos", vehiculoClienteService.obtenerVehiculosPorUsuario(usuarioId));

		long pendientes = reservas.stream()
				.filter(r -> r.getEstado() != null && r.getEstado().equalsIgnoreCase("pendiente")).count();
		long activas = reservas.stream()
				.filter(r -> r.getEstado() != null && r.getEstado().equalsIgnoreCase("activa")).count();
		long completadas = reservas.stream()
				.filter(r -> r.getEstado() != null && r.getEstado().equalsIgnoreCase("completada")).count();

		model.addAttribute("pendientes", pendientes);
		model.addAttribute("activas", activas);
		model.addAttribute("completadas", completadas);

		return "clientes/reservasCliente";
	}

	@PostMapping("/reservasCliente/crear")
	public String crearReservaCliente(@RequestParam("servicioId") Integer servicioId,
			@RequestParam("fechaReserva") String fechaReserva,
			@RequestParam("horaReserva") String horaReserva,
			@RequestParam(name = "trabajadorId", required = false) Integer trabajadorId,
			@RequestParam(name = "vehiculoId", required = false) Integer vehiculoId,
			HttpSession session,
			RedirectAttributes redirectAttributes) {

		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId == null) {
			return "redirect:/login";
		}

		try {
			Usuario usuario = usuarioService.findById(usuarioId);
			Servicio servicio = servicioService.findById(servicioId);
			if (usuario == null || servicio == null) {
				redirectAttributes.addFlashAttribute("error", "No se pudo crear la reserva. Datos inválidos.");
			} else {
				Reserva reserva = new Reserva();
				reserva.setUsuario(usuario);
				reserva.setServicio(servicio);
				reserva.setFechaReserva(fechaReserva);
				reserva.setHoraReserva(horaReserva);
				reserva.setIdCliente(usuarioId);
				if (trabajadorId != null) {
					var trabajador = trabajadorService.findById(trabajadorId);
					reserva.setTrabajador(trabajador);
				}
				if (vehiculoId != null) {
					var vehiculo = vehiculoClienteService.obtenerPorId(vehiculoId);
					reserva.setVehiculo(vehiculo);
				}
				reserva.setEstado("pendiente");
				reservaService.save(reserva);
				redirectAttributes.addFlashAttribute("success", "Reserva creada correctamente.");

			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al crear la reserva.");
		}

		return "redirect:/reservasCliente";
	}

	@PostMapping("/reservasCliente/cancelar/{id}")
	public String cancelarReservaCliente(@PathVariable("id") Integer id,
			HttpSession session,
			RedirectAttributes redirectAttributes) {

		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId == null) {
			return "redirect:/login";
		}

		try {
			Reserva reserva = reservaService.findById(id);
			if (reserva == null || reserva.getUsuario() == null || !usuarioId.equals(reserva.getUsuario().getId_usuario())) {
				redirectAttributes.addFlashAttribute("error", "No se encontró la reserva o no te pertenece.");
			} else {
				reserva.setEstado("cancelada");
				reservaService.save(reserva);
				redirectAttributes.addFlashAttribute("success", "Reserva cancelada correctamente.");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Ocurrió un error al cancelar la reserva.");
		}

		return "redirect:/reservasCliente";
	}

	@GetMapping("/historial")
	public String historial(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			model.addAttribute("usuario", usuario);
		}
		return "clientes/historialCliente"; // ← O el nombre que uses
	}

	@GetMapping("/servicios")
	public String servicios(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		if (usuarioId != null) {
			Usuario usuario = usuarioService.findById(usuarioId);
			model.addAttribute("usuario", usuario);
		}
		return "clientes/serviciosCliente"; // ← O el nombre que uses
	}
}