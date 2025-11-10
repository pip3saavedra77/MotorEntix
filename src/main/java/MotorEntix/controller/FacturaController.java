package MotorEntix.controller;

import MotorEntix.model.Factura;
import MotorEntix.service.IFacturaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final IFacturaService facturaService;

    public FacturaController(IFacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaService.findAll();
    }

    @GetMapping("/{id}")
    public Factura getFacturaById(@PathVariable Integer id) {
        return facturaService.findById(id);
    }

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturaService.save(factura);
    }

    @PutMapping("/{id}")
    public Factura updateFactura(@PathVariable Integer id, @RequestBody Factura factura) {
        factura.setId(id); // tu entidad Factura debe tener setId()
        return facturaService.save(factura);
    }

    @DeleteMapping("/{id}")
    public void deleteFactura(@PathVariable Integer id) {
        facturaService.deleteById(id);
    }
}
