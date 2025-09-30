package MotorEntix.service.impl;

import MotorEntix.model.Servicio;
import MotorEntix.repository.IServicioRepository;
import MotorEntix.service.IServicioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImplement implements IServicioService {

    private final IServicioRepository servicioRepository;

    public ServicioServiceImplement(IServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio findById(Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void deleteById(Integer id) {
        servicioRepository.deleteById(id);
    }
}
