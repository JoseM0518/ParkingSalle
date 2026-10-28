package com.devs.ParkingSalle.repository;

import com.devs.ParkingSalle.model.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReciboRepository extends JpaRepository<Recibo,Long> {
}

