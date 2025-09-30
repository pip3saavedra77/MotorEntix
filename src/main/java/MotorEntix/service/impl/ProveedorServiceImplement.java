package MotorEntix.service.impl;

import MotorEntix.model.Proveedor;
import MotorEntix.repository.IProveedorRepository;
import MotorEntix.service.IProveedorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplement implements IProveedorService {

    private final IProveedorRepository proveedorRepository;

    public ProveedorServiceImplement(IProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteById(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
