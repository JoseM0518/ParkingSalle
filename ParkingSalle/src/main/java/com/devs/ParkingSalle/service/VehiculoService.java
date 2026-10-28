package com.devs.ParkingSalle.service;

import com.devs.ParkingSalle.dto.VehiculoDto;
import com.devs.ParkingSalle.model.Rol;
import com.devs.ParkingSalle.model.Vehiculo;
import com.devs.ParkingSalle.repository.IRolRepository;
import com.devs.ParkingSalle.repository.IVehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService implements IVehiculoService{


    private final IVehiculoRepository vhRepo;
    private final IRolRepository rlRepo;

    public VehiculoService(IVehiculoRepository vhRepo, IRolRepository rlRepo) {
        this.vhRepo = vhRepo;
        this.rlRepo = rlRepo;
    }

    @Override
    public VehiculoDto registrarVehiculo(VehiculoDto vhDto) {
        if (vhRepo.findByPlaca(vhDto.getPlaca()).isPresent()){
            throw new RuntimeException("ya existe un  vehiculo con la placa que ingresaste");

        }

        Rol rol = rlRepo.findById(vhDto.getIdRol()).orElseThrow(() -> new RuntimeException("El rol no existe"));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(vhDto.getPlaca());
        vehiculo.setTipoVehiculo(vhDto.getTipoVehiculo());
        vehiculo.setRol(rol);

        Vehiculo vhGuardado = vhRepo.save(vehiculo);

        return convertirDto(vhGuardado);
    }

    @Override
    public VehiculoDto buscarVehiculoPlaca(String placa) {
        Vehiculo vehiculo = vhRepo.findByPlaca(placa).orElseThrow(() -> new RuntimeException("No se encontró la placa"));
        return convertirDto(vehiculo);
    }

    @Override
    public List<VehiculoDto> listarVehiculos() {
        return vhRepo.findAll().stream().map(this:: convertirDto).toList();
    }

    @Override
    public VehiculoDto editarVehiculo(String placa, VehiculoDto vhDto) {
        Vehiculo vehiculoExistente = vhRepo.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado para actualizar"));

        Rol rol = rlRepo.findById(vhDto.getIdRol())
                .orElseThrow(() -> new RuntimeException("El rol especificado no existe"));

        if (vhDto.getTipoVehiculo() != null) {
            int tipo = vhDto.getTipoVehiculo();
            if (tipo != 0 && tipo != 1) {
                throw new RuntimeException("El tipo de vehículo solo puede ser 0 (Carro) o 1 (Moto)");
            }
        }

        if (!placa.equals(vhDto.getPlaca())
                && vhRepo.findByPlaca(vhDto.getPlaca()).isPresent()) {
            throw new RuntimeException("Ya existe otro vehículo con esa placa");
        }

        vehiculoExistente.setPlaca(vhDto.getPlaca());
        vehiculoExistente.setTipoVehiculo(vhDto.getTipoVehiculo());
        vehiculoExistente.setRol(rol);

        Vehiculo vehiculoActualizado = vhRepo.save(vehiculoExistente);
        return convertirDto(vehiculoActualizado);
    }

    @Override
    public void eliminarVehiculo(String placa) {
       Vehiculo vehiculoEl = vhRepo.findByPlaca(placa).orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));
       vhRepo.delete(vehiculoEl);
    }

    private VehiculoDto convertirDto(Vehiculo vehiculo) {
        VehiculoDto vhDto = new VehiculoDto();
        vhDto.setIdVehiculo(vehiculo.getIdVehiculo());
        vhDto.setPlaca(vehiculo.getPlaca());
        vhDto.setTipoVehiculo(vehiculo.getTipoVehiculo());

        if (vehiculo.getRol() != null) {
            vhDto.setIdRol(vehiculo.getRol().getIdRol());
            vhDto.setNombreRol(vehiculo.getRol().getNombreRol());
        }

        return vhDto;
    }
}
