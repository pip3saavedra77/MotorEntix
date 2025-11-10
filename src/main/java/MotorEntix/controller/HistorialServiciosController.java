package MotorEntix.controller;

import MotorEntix.model.HistorialServicios;
import MotorEntix.service.IHistorialServiciosService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/historialservicios")
public class HistorialServiciosController {

    private final IHistorialServiciosService historialServiciosService;

    public HistorialServiciosController(IHistorialServiciosService historialServiciosService) {
        this.historialServiciosService = historialServiciosService;
    }

    @GetMapping
    public List<HistorialServicios> getAllHistoriales() {
        return historialServiciosService.findAll();
    }

    @GetMapping("/{id}")
    public HistorialServicios getHistorialById(@PathVariable Integer id) {
        return historialServiciosService.findById(id);
    }

    @PostMapping
    public HistorialServicios createHistorial(@RequestBody HistorialServicios historial) {
        return historialServiciosService.save(historial);
    }

    @PutMapping("/{id}")
    public HistorialServicios updateHistorial(@PathVariable Integer id, @RequestBody HistorialServicios historial) {
        historial.setId(id); // tu entidad HistorialServicios debe tener setId()
        return historialServiciosService.save(historial);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorial(@PathVariable Integer id) {
        historialServiciosService.deleteById(id);
    }
}
