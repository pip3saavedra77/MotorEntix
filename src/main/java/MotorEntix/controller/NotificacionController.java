package MotorEntix.controller;

import MotorEntix.model.Notificacion;
import MotorEntix.service.INotificacionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final INotificacionService notificacionService;

    public NotificacionController(INotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public List<Notificacion> getAllNotificaciones() {
        return notificacionService.findAll();
    }

    @GetMapping("/{id}")
    public Notificacion getNotificacionById(@PathVariable Integer id) {
        return notificacionService.findById(id);
    }

    @PostMapping
    public Notificacion createNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.save(notificacion);
    }

    @PutMapping("/{id}")
    public Notificacion updateNotificacion(@PathVariable Integer id, @RequestBody Notificacion notificacion) {
        notificacion.setId(id); // tu entidad Notificacion debe tener setId()
        return notificacionService.save(notificacion);
    }

    @DeleteMapping("/{id}")
    public void deleteNotificacion(@PathVariable Integer id) {
        notificacionService.deleteById(id);
    }
}
