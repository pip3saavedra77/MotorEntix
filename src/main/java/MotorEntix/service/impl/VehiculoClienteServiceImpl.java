package MotorEntix.service.impl;

import MotorEntix.model.Vehiculo;
import MotorEntix.model.Usuario;
import MotorEntix.repository.IVehiculoRepository;
import MotorEntix.service.IVehiculoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoClienteServiceImpl implements IVehiculoClienteService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	public List<Vehiculo> obtenerVehiculosPorUsuario(Integer idUsuario) {
		return vehiculoRepository.findByUsuarioId(idUsuario);
	}

	@Override
	public boolean existePlaca(String placa) {
		return vehiculoRepository.existsByPlaca(placa);
	}

	@Override
	public boolean existePlacaEnOtroVehiculo(String placa, Integer idVehiculoActual) {
		Vehiculo vehiculoActual = obtenerPorId(idVehiculoActual);
		if (vehiculoActual == null || vehiculoActual.getUsuario() == null) {
			return false;
		}
		Integer idUsuarioActual = vehiculoActual.getUsuario().getId_usuario();
		return vehiculoRepository.existsByPlacaAndUsuarioIdNot(placa, idUsuarioActual);
	}

	@Override
	public Vehiculo guardarVehiculoConUsuario(Vehiculo vehiculo, Usuario usuario) {
		vehiculo.setUsuario(usuario);
		if (vehiculo.getEstadoVehiculo() == null || vehiculo.getEstadoVehiculo().isEmpty()) {
			vehiculo.setEstadoVehiculo("Activo");
		}
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
}