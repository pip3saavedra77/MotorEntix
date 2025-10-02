package MotorEntix.service;

import java.util.List;
import MotorEntix.model.Vehiculo;

public interface IVehiculoService {
	List<Vehiculo> listarTodos();

	Vehiculo guardar(Vehiculo vehiculo);

	Vehiculo obtenerPorId(Integer id);

	void eliminar(Integer id);
}
