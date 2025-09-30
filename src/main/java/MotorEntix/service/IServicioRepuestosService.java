package MotorEntix.service;

import java.util.List;
import MotorEntix.model.ServicioRepuestos;

public interface IServicioRepuestosService {
    List<ServicioRepuestos> findAll();
    ServicioRepuestos findById(Integer id);
    ServicioRepuestos save(ServicioRepuestos servicioRepuestos);
    void deleteById(Integer id);
}
