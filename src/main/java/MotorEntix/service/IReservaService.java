package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Reserva;

public interface IReservaService {
    List<Reserva> findAll();
    Reserva findById(Integer id);
    Reserva save(Reserva reserva);
    void deleteById(Integer id);
}
