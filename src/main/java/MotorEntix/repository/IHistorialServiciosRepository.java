package MotorEntix.repository;

import MotorEntix.model.HistorialServicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistorialServiciosRepository extends JpaRepository<HistorialServicios, Integer> {
}
