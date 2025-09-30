package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Notificacion;

public interface INotificacionService {
    List<Notificacion> findAll();
    Notificacion findById(Integer id);
    Notificacion save(Notificacion notificacion);
    void deleteById(Integer id);
}
