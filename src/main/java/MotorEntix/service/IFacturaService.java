package MotorEntix.service;

import MotorEntix.model.Factura;
import java.util.List;

public interface IFacturaService {
    List<Factura> findAll();
    Factura findById(Integer id);
    Factura save(Factura factura);
    void deleteById(Integer id);
}
