package MotorEntix.service.impl;

import MotorEntix.model.Reserva;
import MotorEntix.repository.IReservaRepository;
import MotorEntix.service.IReservaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImplement implements IReservaService {

    private final IReservaRepository reservaRepository;

    public ReservaServiceImplement(IReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva findById(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteById(Integer id) {
        reservaRepository.deleteById(id);
    }
}
