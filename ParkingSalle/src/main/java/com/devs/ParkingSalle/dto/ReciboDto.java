package com.devs.ParkingSalle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReciboDto {

    private Long idRecibo;
    private BigDecimal valorNeto;
    private java.time.LocalDate fechaRecibo;
    private String placa;
    private int tipoVehiculo;
    private String nombreRol;
}
