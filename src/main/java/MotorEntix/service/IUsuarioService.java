package MotorEntix.service;

import java.util.Optional;
import MotorEntix.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> validarUsuario(String correo, String contrasena);

	// Agregar estos métodos para el registro
	Usuario registrarUsuario(Usuario usuario);

	Optional<Usuario> buscarPorCorreo(String correo);

	boolean existePorCorreo(String correo);
}