package MotorEntix.repository;

import MotorEntix.model.ServicioRepuestos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioRepuestosRepository extends JpaRepository<ServicioRepuestos, Integer> {
}
