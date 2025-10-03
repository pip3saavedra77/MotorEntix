package MotorEntix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MotorEntix.model.Vehiculo;
import MotorEntix.repository.IVehiculoRepository;
import MotorEntix.service.IVehiculoService;

import java.util.List;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	public List<Vehiculo> listarTodos() {
		List<Vehiculo> lista = vehiculoRepository.findAll();
		System.out.println("Vehiculos encontrados: " + lista.size());
		return lista;
	}

	@Override
	public Vehiculo guardar(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public Vehiculo obtenerPorId(Integer id) {
		return vehiculoRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		vehiculoRepository.deleteById(id);
	}

	@Override
	public List<Vehiculo> buscarPorTermino(String termino) {
		// TODO Auto-generated method stub
		return vehiculoRepository.buscarPorTermino(termino);
	}
}
