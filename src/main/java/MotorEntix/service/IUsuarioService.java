package MotorEntix.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import MotorEntix.model.Usuario;
import MotorEntix.repository.IUsuarioRepository;


@Service
public class IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public IUsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> validarUsuario(String correo, String contraseña) {
        return usuarioRepository.findByCorreoAndContraseña(correo, contraseña);
    }
}
