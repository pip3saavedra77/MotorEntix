package MotorEntix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import MotorEntix.model.Usuario;
import MotorEntix.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PanelTrabajadorController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/panel.trabajador")
    public String mostrarPanelTrabajador(Model model, HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (rol == null) {
            return "redirect:/login";
        }
        switch (rol.toLowerCase()) {
        case "trabajador":
            break; // permitido
        case "administrador":
            return "redirect:/admin/panel";
        case "cliente":
            return "redirect:/panel.cliente";
        case "dueno":
            return "redirect:/panel.dueno";
        default:
            return "redirect:/login";
        }
        agregarUsuarioAlModelo(model, session);
        return "trabajadores/panelTrabajador";
    }

    @GetMapping("/trabajador/servicios-asignados")
    public String serviciosAsignados(Model model, HttpSession session) {
        agregarUsuarioAlModelo(model, session);
        return "trabajadores/serviciosAsignados";
    }

    @GetMapping("/trabajador/agenda")
    public String agenda(Model model, HttpSession session) {
        agregarUsuarioAlModelo(model, session);
        return "trabajadores/agenda";
    }

    @GetMapping("/trabajador/historial-servicios")
    public String historialServicios(Model model, HttpSession session) {
        agregarUsuarioAlModelo(model, session);
        return "trabajadores/historialServicios";
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
