package MotorEntix.service;

import MotorEntix.model.Vehiculo;
import MotorEntix.model.Usuario;
import java.util.List;

public interface IVehiculoClienteService {
	List<Vehiculo> obtenerVehiculosPorUsuario(Integer idUsuario);

	boolean existePlaca(String placa);

	boolean existePlacaEnOtroVehiculo(String placa, Integer idVehiculoActual);

	Vehiculo guardarVehiculoConUsuario(Vehiculo vehiculo, Usuario usuario);

	Vehiculo obtenerPorId(Integer id);

	void eliminar(Integer id);
}