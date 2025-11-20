package MotorEntix.service;

import java.util.List;
import java.util.Optional;

import MotorEntix.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> validarUsuario(String correo, String contrasena);

	Usuario registrarUsuario(Usuario usuario);

	Optional<Usuario> buscarPorCorreo(String correo);

	boolean existePorCorreo(String correo);


	// NUEVOS MÉTODOS PARA EL PERFIL
	Usuario findById(Integer id);

	Usuario findByCorreo(String correo);
	
	Usuario actualizarUsuario(Usuario usuario);

	// Listar usuarios por rol (ej. clientes)
	List<Usuario> findByRol(String rol);

	// Listar todos los usuarios
	List<Usuario> findAll();

	// Eliminar usuario por id
	void eliminarPorId(Integer id);

	// Buscar usuarios por texto (nombre, apellido o correo)
	List<Usuario> buscarPorTexto(String texto);
}