package MotorEntix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MotorEntix.model.Vehiculo;
import MotorEntix.repository.IVehiculoRepository;
import MotorEntix.service.IVehiculoService;

import java.util.List;

@Service
public class VehiculoServiceImplement implements IVehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarTodos() {
        List<Vehiculo> lista = vehiculoRepository.findAll();
        System.out.println("Vehiculos encontrados: " + lista.size());
        return lista;
    }
    
    @Override
    public  Vehiculo guardar(Vehiculo vehiculo) {
    	return vehiculoRepository.save(vehiculo);
    }
    
    
}