package com.devs.ParkingSalle.repository;

import com.devs.ParkingSalle.model.Rol;
import com.devs.ParkingSalle.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {

    Optional<Rol> findByNombreRol(String nombreRol);
}
