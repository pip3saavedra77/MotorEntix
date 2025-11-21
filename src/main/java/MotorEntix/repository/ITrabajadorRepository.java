package MotorEntix.repository;

import MotorEntix.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrabajadorRepository extends JpaRepository<Trabajador, Integer> {

    @Query("SELECT t FROM Trabajador t WHERE t.usuario.id_usuario = :usuarioId")
    Trabajador findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
