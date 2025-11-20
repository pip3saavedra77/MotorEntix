package MotorEntix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import MotorEntix.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);

	// Agregar estos métodos para el registro
	Optional<Usuario> findByCorreo(String correo);

	boolean existsByCorreo(String correo);

	// Listar usuarios por rol
	List<Usuario> findByRolIgnoreCase(String rol);

	// Buscar por nombre, apellido o correo que contenga el texto (ignorando mayúsculas)
	List<Usuario> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrCorreoContainingIgnoreCase(
		String nombre, String apellido, String correo);
}