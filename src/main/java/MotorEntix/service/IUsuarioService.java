package MotorEntix.service;

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
}