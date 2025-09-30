package MotorEntix.service.impl;

import MotorEntix.model.Inventario;
import MotorEntix.repository.IInventarioRepository;
import MotorEntix.service.IInventarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImplement implements IInventarioService {

    private final IInventarioRepository inventarioRepository;

    public InventarioServiceImplement(IInventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario findById(Integer id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    @Override
    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public void deleteById(Integer id) {
        inventarioRepository.deleteById(id);
    }
}
