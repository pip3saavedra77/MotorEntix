package MotorEntix.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import MotorEntix.model.Vehiculo;
import MotorEntix.service.IVehiculoService;

import java.util.List;

@Controller
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    

    @GetMapping("/vehiculos")
    public String listarVehiculos() {
        return "redirect:/admin/panel-admin";
    }
    
    
    
    
}


