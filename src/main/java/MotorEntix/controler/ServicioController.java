package MotorEntix.controler;

import MotorEntix.model.Servicio;
import MotorEntix.service.IServicioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    private final IServicioService servicioService;

    public ServicioController(IServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public Servicio getServicioById(@PathVariable Integer id) {
        return servicioService.findById(id);
    }

    @PostMapping
    public Servicio createServicio(@RequestBody Servicio servicio) {
        return servicioService.save(servicio);
    }

    @PutMapping("/{id}")
    public Servicio updateServicio(@PathVariable Integer id, @RequestBody Servicio servicio) {
        servicio.setId(id); // tu entidad Servicio debe tener setId()
        return servicioService.save(servicio);
    }

    @DeleteMapping("/{id}")
    public void deleteServicio(@PathVariable Integer id) {
        servicioService.deleteById(id);
    }
}
