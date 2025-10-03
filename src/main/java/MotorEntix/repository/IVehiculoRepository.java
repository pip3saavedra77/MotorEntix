package MotorEntix.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import MotorEntix.model.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    
    @Query("SELECT v FROM Vehiculo v WHERE " +
           "LOWER(v.placa) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(v.marca) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(v.modelo) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(v.color) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(v.tipoVehiculo) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(v.estadoVehiculo) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Vehiculo> buscarPorTermino(@Param("termino") String termino);
}