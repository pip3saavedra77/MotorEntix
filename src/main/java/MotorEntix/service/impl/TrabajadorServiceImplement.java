package MotorEntix.service.impl;

import MotorEntix.model.Trabajador;
import MotorEntix.repository.ITrabajadorRepository;
import MotorEntix.service.ITrabajadorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorServiceImplement implements ITrabajadorService {

    private final ITrabajadorRepository trabajadorRepository;

    public TrabajadorServiceImplement(ITrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @Override
    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }

    @Override
    public Trabajador findById(Integer id) {
        return trabajadorRepository.findById(id).orElse(null);
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public void deleteById(Integer id) {
        trabajadorRepository.deleteById(id);
    }
}
