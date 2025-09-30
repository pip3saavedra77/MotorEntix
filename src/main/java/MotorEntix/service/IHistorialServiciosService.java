package MotorEntix.service;

import java.util.List;
import MotorEntix.model.HistorialServicios;

public interface IHistorialServiciosService {
    List<HistorialServicios> findAll();
    HistorialServicios findById(Integer id);
    HistorialServicios save(HistorialServicios historialServicios);
    void deleteById(Integer id);
}
