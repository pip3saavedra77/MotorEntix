package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Inventario;

public interface IInventarioService {
    List<Inventario> findAll();
    Inventario findById(Integer id);
    Inventario save(Inventario inventario);
    void deleteById(Integer id);
}
