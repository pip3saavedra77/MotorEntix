package MotorEntix.repository;

import java.util.List;

import MotorEntix.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("SELECT r FROM Reserva r WHERE r.usuario.id_usuario = :usuarioId")
	List<Reserva> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
