package MotorEntix.service;

import MotorEntix.model.Vehiculo;
import java.util.List;

public interface IVehiculoService {
	List<Vehiculo> listarTodos();

	Vehiculo guardar(Vehiculo vehiculo);

	Vehiculo obtenerPorId(Integer id);

	void eliminar(Integer id);

	List<Vehiculo> buscarPorTermino(String search);
}