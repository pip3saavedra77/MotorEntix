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
}
