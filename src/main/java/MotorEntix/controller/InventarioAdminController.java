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

import MotorEntix.model.Inventario;
import MotorEntix.service.IInventarioService;
import MotorEntix.service.IProveedorService;

@Controller
@RequestMapping("/admin/inventario")
public class InventarioAdminController {

    @Autowired
    private IInventarioService inventarioService;

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping("/lista")
    public String listarInventario(Model model) {
        List<Inventario> inventarios = inventarioService.findAll();
        model.addAttribute("inventarios", inventarios);
        model.addAttribute("inventario", new Inventario());
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("editMode", false);
        model.addAttribute("pagina", "inventario");
        return "administrador/inventarioAdmin";
    }

    @GetMapping("/editar/{id}")
    public String editarInventario(@PathVariable Integer id, Model model) {
        Inventario inventario = inventarioService.findById(id);
        List<Inventario> inventarios = inventarioService.findAll();
        model.addAttribute("inventarios", inventarios);
        model.addAttribute("inventario", inventario);
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("editMode", true);
        model.addAttribute("pagina", "inventario");
        return "administrador/inventarioAdmin";
    }

    @PostMapping("/guardar")
    public String guardarInventario(@ModelAttribute Inventario inventario) {
        inventarioService.save(inventario);
        return "redirect:/admin/inventario/lista";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarInventario(@PathVariable Integer id) {
        inventarioService.deleteById(id);
        return "redirect:/admin/inventario/lista";
    }
}
