package MotorEntix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import MotorEntix.model.Proveedor;
import MotorEntix.model.Usuario;
import MotorEntix.service.IProveedorService;
import MotorEntix.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/proveedor")
public class ProveedorAdminController {

    @Autowired
    private IProveedorService proveedorService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listarProveedores(Model model, HttpSession session) {
        agregarUsuarioAlModelo(model, session);
        List<Proveedor> proveedores = proveedorService.findAll();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("editMode", false);
        model.addAttribute("pagina", "proveedor");
        return "administrador/proveedor";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable Integer id, Model model, HttpSession session) {
        agregarUsuarioAlModelo(model, session);
        Proveedor proveedor = proveedorService.findById(id);
        List<Proveedor> proveedores = proveedorService.findAll();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("editMode", true);
        model.addAttribute("pagina", "proveedor");
        return "administrador/proveedor";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.save(proveedor);
        return "redirect:/admin/proveedor";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Integer id) {
        proveedorService.deleteById(id);
        return "redirect:/admin/proveedor";
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
