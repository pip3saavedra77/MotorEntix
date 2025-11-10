package MotorEntix.service.impl;

import MotorEntix.model.Vehiculo;
import MotorEntix.repository.IVehiculoRepository;
import MotorEntix.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	public List<Vehiculo> listarTodos() {
		return vehiculoRepository.findAll();
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
	public List<Vehiculo> buscarPorTermino(String search) {
		return vehiculoRepository.buscarPorTermino(search);
	}
}