package MotorEntix.controller;

import MotorEntix.model.Inventario;
import MotorEntix.service.IInventarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    private final IInventarioService inventarioService;

    public InventarioController(IInventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public List<Inventario> getAllInventarios() {
        return inventarioService.findAll();
    }

    @GetMapping("/{id}")
    public Inventario getInventarioById(@PathVariable Integer id) {
        return inventarioService.findById(id);
    }

    @PostMapping
    public Inventario createInventario(@RequestBody Inventario inventario) {
        return inventarioService.save(inventario);
    }

    @PutMapping("/{id}")
    public Inventario updateInventario(@PathVariable Integer id, @RequestBody Inventario inventario) {
        inventario.setId(id); // tu entidad Inventario debe tener setId()
        return inventarioService.save(inventario);
    }

    @DeleteMapping("/{id}")
    public void deleteInventario(@PathVariable Integer id) {
        inventarioService.deleteById(id);
    }
}
