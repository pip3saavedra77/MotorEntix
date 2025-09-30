package MotorEntix.service.impl;

import MotorEntix.model.HistorialServicios;
import MotorEntix.repository.IHistorialServiciosRepository;
import MotorEntix.service.IHistorialServiciosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialServiciosServiceImplement implements IHistorialServiciosService {

    private final IHistorialServiciosRepository historialServiciosRepository;

    public HistorialServiciosServiceImplement(IHistorialServiciosRepository historialServiciosRepository) {
        this.historialServiciosRepository = historialServiciosRepository;
    }

    @Override
    public List<HistorialServicios> findAll() {
        return historialServiciosRepository.findAll();
    }

    @Override
    public HistorialServicios findById(Integer id) {
        return historialServiciosRepository.findById(id).orElse(null);
    }

    @Override
    public HistorialServicios save(HistorialServicios historialServicios) {
        return historialServiciosRepository.save(historialServicios);
    }

    @Override
    public void deleteById(Integer id) {
        historialServiciosRepository.deleteById(id);
    }
}
