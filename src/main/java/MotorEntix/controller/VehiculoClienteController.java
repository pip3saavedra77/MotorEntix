package MotorEntix.controller;

import MotorEntix.model.Vehiculo;
import MotorEntix.model.Usuario;
import MotorEntix.service.IVehiculoClienteService;
import MotorEntix.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mis-vehiculos")
public class VehiculoClienteController {

    @Autowired
    private IVehiculoClienteService vehiculoService;

    @Autowired
    private IUsuarioService usuarioService;

    @ModelAttribute("vehiculo")
    public Vehiculo getVehiculo() {
        return new Vehiculo();
    }

    @GetMapping
    public String listarVehiculos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        System.out.println("DEBUG - Usuario en sesión: " + (usuario != null ? usuario.getNombre() : "null"));
        
        if (usuario == null) {
            System.out.println("DEBUG - Redirigiendo a login");
            return "redirect:/login";
        }

        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorUsuario(usuario.getId_usuario());
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("usuario", usuario);
        
        return "clientes/vehiculosCliente";
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo, 
                                 HttpSession session, 
                                 RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            if (vehiculoService.existePlaca(vehiculo.getPlaca())) {
                redirectAttributes.addFlashAttribute("error", "La placa '" + vehiculo.getPlaca() + "' ya está registrada");
                return "redirect:/mis-vehiculos";
            }

            vehiculoService.guardarVehiculoConUsuario(vehiculo, usuario);
            redirectAttributes.addFlashAttribute("success", "Vehículo registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el vehículo: " + e.getMessage());
        }

        return "redirect:/mis-vehiculos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Integer id, 
                                  HttpSession session, 
                                  RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            Vehiculo vehiculo = vehiculoService.obtenerPorId(id);
            if (vehiculo != null && vehiculo.getUsuario().getId_usuario().equals(usuario.getId_usuario())) {
                vehiculoService.eliminar(id);
                redirectAttributes.addFlashAttribute("success", "Vehículo eliminado exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "No tienes permisos para eliminar este vehículo");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el vehículo: " + e.getMessage());
        }

        return "redirect:/mis-vehiculos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVehiculo(@PathVariable Integer id,
                                     @ModelAttribute Vehiculo datos,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            Vehiculo existente = vehiculoService.obtenerPorId(id);
            if (existente == null || existente.getUsuario() == null ||
                !existente.getUsuario().getId_usuario().equals(usuario.getId_usuario())) {
                redirectAttributes.addFlashAttribute("error", "No tienes permisos para editar este vehículo");
                return "redirect:/mis-vehiculos";
            }

            String nuevaPlaca = datos.getPlaca();
            if (nuevaPlaca != null && !nuevaPlaca.equalsIgnoreCase(existente.getPlaca())) {
                if (vehiculoService.existePlaca(nuevaPlaca)) {
                    redirectAttributes.addFlashAttribute("error", "La placa '" + nuevaPlaca + "' ya está registrada");
                    return "redirect:/mis-vehiculos";
                }
            }

            // Actualizar campos permitidos
            existente.setPlaca(datos.getPlaca());
            existente.setMarca(datos.getMarca());
            existente.setModelo(datos.getModelo());
            existente.setAnio(datos.getAnio());
            existente.setColor(datos.getColor());
            existente.setTipoVehiculo(datos.getTipoVehiculo());
            existente.setTransmicion(datos.getTransmicion());
            existente.setCombustible(datos.getCombustible());
            existente.setVersion(datos.getVersion());
            existente.setDescripcion(datos.getDescripcion());
            if (datos.getEstadoVehiculo() != null && !datos.getEstadoVehiculo().isEmpty()) {
                existente.setEstadoVehiculo(datos.getEstadoVehiculo());
            }

            vehiculoService.guardarVehiculoConUsuario(existente, usuario);
            redirectAttributes.addFlashAttribute("success", "Vehículo actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el vehículo: " + e.getMessage());
        }

        return "redirect:/mis-vehiculos";
    }
}