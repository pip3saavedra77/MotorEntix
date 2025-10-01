package MotorEntix.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        // Para el formulario (si lo usas en la misma vista)
        model.addAttribute("vehiculo", new Vehiculo());

        return "panel.admin"; // → templates/panel.admin/panel.admin.html
    }

    // 📌 Mostrar formulario de registro de vehículos
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "registrarVehiculo"; // → templates/registrarVehiculo.html
    }

    // 📌 Guardar vehículo
    @PostMapping("/vehiculos/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.guardar(vehiculo);
        return "redirect:/admin/panel"; // ✅ Redirige al panel para ver la lista actualizada
    }

    // ================= Otras secciones del panel =================

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