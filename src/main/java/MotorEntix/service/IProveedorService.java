package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Proveedor;

public interface IProveedorService {
    List<Proveedor> findAll();
    Proveedor findById(Integer id);
    Proveedor save(Proveedor proveedor);
    void deleteById(Integer id);
}
