package com.devs.ParkingSalle.repository;

import com.devs.ParkingSalle.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo,Long> {
    Optional<Vehiculo> findByPlaca(String placa);
}
