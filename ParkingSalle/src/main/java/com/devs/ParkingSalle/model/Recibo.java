package com.devs.ParkingSalle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recibos")
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idRecibo;
    private BigDecimal valorNeto;
    private java.time.LocalDate fechaRecibo;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;
}
