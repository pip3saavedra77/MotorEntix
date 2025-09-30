package MotorEntix.service.impl;

import MotorEntix.model.ServicioRepuestos;
import MotorEntix.repository.IServicioRepuestosRepository;
import MotorEntix.service.IServicioRepuestosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRepuestosServiceImplement implements IServicioRepuestosService {

    private final IServicioRepuestosRepository servicioRepuestosRepository;

    public ServicioRepuestosServiceImplement(IServicioRepuestosRepository servicioRepuestosRepository) {
        this.servicioRepuestosRepository = servicioRepuestosRepository;
    }

    @Override
    public List<ServicioRepuestos> findAll() {
        return servicioRepuestosRepository.findAll();
    }

    @Override
    public ServicioRepuestos findById(Integer id) {
        return servicioRepuestosRepository.findById(id).orElse(null);
    }

    @Override
    public ServicioRepuestos save(ServicioRepuestos servicioRepuestos) {
        return servicioRepuestosRepository.save(servicioRepuestos);
    }

    @Override
    public void deleteById(Integer id) {
        servicioRepuestosRepository.deleteById(id);
    }
}
