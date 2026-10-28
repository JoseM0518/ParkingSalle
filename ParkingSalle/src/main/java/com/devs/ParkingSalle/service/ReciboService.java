package com.devs.ParkingSalle.service;

import com.devs.ParkingSalle.dto.ReciboDto;
import com.devs.ParkingSalle.model.Recibo;
import com.devs.ParkingSalle.model.Vehiculo;
import com.devs.ParkingSalle.repository.IReciboRepository;
import com.devs.ParkingSalle.repository.IVehiculoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
public class ReciboService implements IReciboService{

    private final IReciboRepository recRepo;

    private final IVehiculoRepository vehRepo;

    public ReciboService(IReciboRepository recRepo, IVehiculoRepository vehRepo) {
        this.recRepo = recRepo;
        this.vehRepo = vehRepo;
    }

    @Override
    public ReciboDto generarRecibo(String placa, BigDecimal valorNeto) {

        Vehiculo vehiculo = vehRepo.findByPlaca(placa).orElseThrow(() -> new RuntimeException("El vehiculo con placa: "+ placa + " no está registrado" ));

        Recibo recibo = new Recibo();

        recibo.setValorNeto(valorNeto);
        recibo.setFechaRecibo(LocalDate.now());
        recibo.setVehiculo(vehiculo);

        Recibo reciboGuardado = recRepo.save(recibo);

        return convertirDto(reciboGuardado);
    }

    @Override
    public ReciboDto obtenerReciboPorId(Long idRecibo) {
        Recibo recibo = recRepo.findById(idRecibo).orElseThrow(() -> new RuntimeException("Recibo no encontrado"));
        return convertirDto(recibo);
    }

    @Override
    public List<ReciboDto> listarRecibos() {
        return recRepo.findAll().stream().map(this::convertirDto).toList();
    }

    private ReciboDto convertirDto(Recibo recibo){

        ReciboDto dto = new ReciboDto();
        dto.setIdRecibo(recibo.getIdRecibo());
        dto.setValorNeto(recibo.getValorNeto());
        dto.setFechaRecibo(recibo.getFechaRecibo());

        if (recibo.getVehiculo() != null){

            dto.setPlaca(recibo.getVehiculo().getPlaca());
            dto.setTipoVehiculo(recibo.getVehiculo().getTipoVehiculo());

            if (recibo.getVehiculo().getRol() != null){

                dto.setNombreRol(recibo.getVehiculo().getRol().getNombreRol());
            }
        }

        return dto;
    }
}
