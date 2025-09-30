package MotorEntix.service.impl;

import MotorEntix.model.Factura;
import MotorEntix.repository.IFacturaRepository;
import MotorEntix.service.IFacturaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImplement implements IFacturaService {

    private final IFacturaRepository facturaRepository;

    // Inyección por constructor
    public FacturaServiceImplement(IFacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(Integer id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(Integer id) {
        facturaRepository.deleteById(id);
    }
}
