package MotorEntix.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import MotorEntix.model.Usuario;
import MotorEntix.repository.IUsuarioRepository;
import MotorEntix.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private final IUsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Optional<Usuario> validarUsuario(String correo, String contrasena) {
		return usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		// Validar que el correo no exista
		if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
			throw new RuntimeException("El correo ya está registrado");
		}

		// Establecer valores por defecto
		usuario.setEstado("activo");
		usuario.setRol("cliente");

		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo);
	}

	@Override
	public boolean existePorCorreo(String correo) {
		return usuarioRepository.existsByCorreo(correo);
	}
}