package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Trabajador;

public interface ITrabajadorService {
    List<Trabajador> findAll();
    Trabajador findById(Integer id);
    Trabajador save(Trabajador trabajador);
    void deleteById(Integer id);
}
