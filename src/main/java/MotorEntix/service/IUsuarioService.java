package MotorEntix.service;

import java.util.Optional;
import MotorEntix.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> validarUsuario(String correo, String contrasena);
}
