package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Servicio;

public interface IServicioService {
    List<Servicio> findAll();
    Servicio findById(Integer id);
    Servicio save(Servicio servicio);
    void deleteById(Integer id);
}
