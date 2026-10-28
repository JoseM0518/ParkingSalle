package com.devs.ParkingSalle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDto {

    private Long idVehiculo;
    private String placa;
    private Integer tipoVehiculo;
    private Integer idRol;
    private String nombreRol;
}
