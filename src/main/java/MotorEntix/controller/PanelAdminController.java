	package MotorEntix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import MotorEntix.model.Usuario;
import MotorEntix.model.Reserva;
import MotorEntix.model.Servicio;
import MotorEntix.model.Trabajador;
import MotorEntix.service.IUsuarioService;
import MotorEntix.service.IReservaService;
import MotorEntix.service.IServicioService;
import MotorEntix.service.ITrabajadorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class PanelAdminController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private ITrabajadorService trabajadorService;

	@Autowired
	private IServicioService servicioService;

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

	// Redirección a la vista de vehículos
	@GetMapping("/vehiculos")
	public String mostrarVehiculos() {
		return "redirect:/admin/vehiculos/lista";
	}

	// Otras secciones del panel
	@GetMapping("/inventario")
	public String inventario(Model model) {
		model.addAttribute("pagina", "inventario");
		return "redirect:/admin/inventario/lista";
	}

	@GetMapping("/mi-perfil")
	public String miPerfil(Model model, HttpSession session) {
		agregarUsuarioAlModelo(model, session);
		model.addAttribute("pagina", "mi-perfil");
		return "administrador/mi-perfil";
	}

	@GetMapping("/clientes")
	public String clientes(@RequestParam(name = "q", required = false) String q, Model model, HttpSession session) {
		agregarUsuarioAlModelo(model, session);
		model.addAttribute("pagina", "clientes");
		if (q != null && !q.trim().isEmpty()) {
			model.addAttribute("usuarios", usuarioService.buscarPorTexto(q.trim()));
			model.addAttribute("q", q.trim());
		} else {
			model.addAttribute("usuarios", usuarioService.findAll());
		}
		return "administrador/clientesAdmin";
	}

	@PostMapping("/clientes/actualizar/{id}")
	public String actualizarUsuarioAdmin(@PathVariable("id") Integer id,
			@RequestParam("rol") String rol,
			@RequestParam("estado") String estado,
			RedirectAttributes redirectAttributes) {
		try {
			Usuario usuario = usuarioService.findById(id);
			if (usuario != null) {
				usuario.setRol(rol);
				usuario.setEstado(estado);
				usuarioService.actualizarUsuario(usuario);

				// Si el usuario tiene rol trabajador, asegurarnos de que exista su registro en Trabajador
				if ("trabajador".equalsIgnoreCase(rol)) {
					Trabajador existente = trabajadorService.findByUsuarioId(usuario.getId_usuario());
					if (existente == null) {
						Trabajador nuevoTrabajador = new Trabajador();
						nuevoTrabajador.setUsuario(usuario);
						// Valores por defecto opcionales
						nuevoTrabajador.setEspecialidad("General");
						nuevoTrabajador.setHora_Ingreso("08:00");
						nuevoTrabajador.setHora_Salida("18:00");
						trabajadorService.save(nuevoTrabajador);
					}
				}
				redirectAttributes.addFlashAttribute("exito", "Usuario actualizado correctamente");
			} else {
				redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al actualizar usuario");
		}
		return "redirect:/admin/clientes";
	}

	@PostMapping("/clientes/eliminar/{id}")
	public String eliminarUsuarioAdmin(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes,
			HttpSession session) {
		// Evitar que el usuario logueado se elimine a sí mismo
		Integer usuarioIdSesion = (Integer) session.getAttribute("usuarioId");
		if (usuarioIdSesion != null && usuarioIdSesion.equals(id)) {
			redirectAttributes.addFlashAttribute("error", "No puedes eliminar tu propio usuario desde el panel.");
			return "redirect:/admin/clientes";
		}
		try {
			usuarioService.eliminarPorId(id);
			redirectAttributes.addFlashAttribute("exito", "Usuario eliminado correctamente");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al eliminar usuario");
		}
		return "redirect:/admin/clientes";
	}

	@GetMapping("/reservas")
	public String reservas(@RequestParam(name = "q", required = false) String q,
			@RequestParam(name = "estado", required = false) String estado,
			Model model, HttpSession session) {
		agregarUsuarioAlModelo(model, session);
		model.addAttribute("pagina", "reservas");

		var reservas = reservaService.findAll();

		// Filtro por texto (cliente, correo, servicio, vehículo)
		if (q != null && !q.trim().isEmpty()) {
			String query = q.trim().toLowerCase();
			reservas = reservas.stream().filter(r -> {
				boolean match = false;
				if (r.getUsuario() != null) {
					String nombreCompleto = (r.getUsuario().getNombre() + " " + r.getUsuario().getApellido()).toLowerCase();
					String correo = r.getUsuario().getCorreo() != null ? r.getUsuario().getCorreo().toLowerCase() : "";
					match |= nombreCompleto.contains(query) || correo.contains(query);
				}
				if (r.getServicio() != null && r.getServicio().getNombre() != null) {
					match |= r.getServicio().getNombre().toLowerCase().contains(query);
				}
				if (r.getVehiculo() != null) {
					String placa = r.getVehiculo().getPlaca() != null ? r.getVehiculo().getPlaca().toLowerCase() : "";
					String descVeh = (r.getVehiculo().getMarca() + " " + r.getVehiculo().getModelo()).toLowerCase();
					match |= placa.contains(query) || descVeh.contains(query);
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

		model.addAttribute("reservas", reservas);
		model.addAttribute("trabajadores", trabajadorService.findAll());
		return "administrador/reservasAdmin";
	}

	@PostMapping("/reservas/actualizar/{id}")
	public String actualizarReservaAdmin(@PathVariable("id") Integer id,
			@RequestParam(name = "trabajadorId", required = false) Integer trabajadorId,
			@RequestParam("estado") String estado,
			RedirectAttributes redirectAttributes) {
		try {
			Reserva reserva = reservaService.findById(id);
			if (reserva != null) {
				// Actualizar estado de la reserva
				reserva.setEstado(estado);

				// Asignar trabajador a la reserva (y opcionalmente al servicio) si se envió
				if (trabajadorId != null) {
					Trabajador trabajador = trabajadorService.findById(trabajadorId);
					if (trabajador != null) {
						// Guardar en la propia reserva para que el panel cliente lo vea
						reserva.setTrabajador(trabajador);
						// Mantener compatibilidad asignando también al servicio si existe
						Servicio servicio = reserva.getServicio();
						if (servicio != null) {
							servicio.setTrabajador(trabajador);
							servicioService.save(servicio);
						}
					}
				}

				// Guardar cambios de la reserva al final
				reservaService.save(reserva);

				redirectAttributes.addFlashAttribute("exito", "Reserva actualizada correctamente");
			} else {
				redirectAttributes.addFlashAttribute("error", "Reserva no encontrada");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al actualizar la reserva");
		}
		return "redirect:/admin/reservas";
	}

	@PostMapping("/reservas/cancelar/{id}")
	public String cancelarReservaAdmin(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			Reserva reserva = reservaService.findById(id);
			if (reserva != null) {
				reserva.setEstado("cancelada");
				reservaService.save(reserva);
				redirectAttributes.addFlashAttribute("exito", "Reserva cancelada correctamente");
			} else {
				redirectAttributes.addFlashAttribute("error", "Reserva no encontrada");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al cancelar la reserva");
		}
		return "redirect:/admin/reservas";
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