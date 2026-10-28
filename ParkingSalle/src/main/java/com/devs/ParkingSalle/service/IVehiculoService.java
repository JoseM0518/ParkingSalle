package com.devs.ParkingSalle.service;

import com.devs.ParkingSalle.dto.VehiculoDto;

import java.util.List;

public interface IVehiculoService {

    VehiculoDto registrarVehiculo(VehiculoDto vhDto);
    VehiculoDto buscarVehiculoPlaca(String placa);
    List<VehiculoDto> listarVehiculos();
    VehiculoDto editarVehiculo(String placa, VehiculoDto vhDto);
    void eliminarVehiculo(String placa);
}
