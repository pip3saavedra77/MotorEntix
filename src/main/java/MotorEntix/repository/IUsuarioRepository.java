package MotorEntix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MotorEntix.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar usuario por correo y contraseña
    Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
}
