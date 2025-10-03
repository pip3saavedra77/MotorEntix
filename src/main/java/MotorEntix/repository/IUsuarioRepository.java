package MotorEntix.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import MotorEntix.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
}
