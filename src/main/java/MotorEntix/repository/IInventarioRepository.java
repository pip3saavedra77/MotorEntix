package MotorEntix.repository;

import MotorEntix.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {
	
}
