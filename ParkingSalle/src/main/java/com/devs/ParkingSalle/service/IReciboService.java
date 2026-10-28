package com.devs.ParkingSalle.service;

import com.devs.ParkingSalle.dto.ReciboDto;

import java.math.BigDecimal;
import java.util.List;

public interface IReciboService {

    ReciboDto generarRecibo(String placa, BigDecimal valorNeto);
    ReciboDto obtenerReciboPorId(Long idRecibo);
    List<ReciboDto> listarRecibos();

}
