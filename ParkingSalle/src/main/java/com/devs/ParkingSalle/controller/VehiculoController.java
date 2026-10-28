package com.devs.ParkingSalle.controller;

import com.devs.ParkingSalle.dto.VehiculoDto;
import com.devs.ParkingSalle.service.IVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {


    private final IVehiculoService vhService;

    public VehiculoController(IVehiculoService vhService) {
        this.vhService = vhService;
    }

    private static final Pattern CARRO_PATTERN = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
    private static final Pattern MOTO_PATTERN = Pattern.compile("^[A-Z]{3}[0-9]{2}[A-Z]$|^[A-Z]{3}[0-9]{2}$");

    @GetMapping
    public ResponseEntity<List<VehiculoDto>> listarVehiculos() {
        return ResponseEntity.ok(vhService.listarVehiculos());
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> buscarVehiculoPorPlaca(@PathVariable String placa) {
        try {
            VehiculoDto vehiculo = vhService.buscarVehiculoPlaca(placa);
            return ResponseEntity.ok(vehiculo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarVehiculo(@RequestBody VehiculoDto vehiculoDto) {
        if (vehiculoDto == null || vehiculoDto.getPlaca() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Los datos del vehículo son obligatorios.");
        }

        String placa = vehiculoDto.getPlaca().toUpperCase().trim();
        int tipoV = vehiculoDto.getTipoVehiculo();

        if (tipoV == 1){
            if (placa.length() != 6 || !CARRO_PATTERN.matcher(placa).matches()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: La placa de carro debe tener exactamente 6 caracteres y cumplir el estándar (ej. ABC123).");
            }
        } else if (tipoV == 0) {
            if (placa.length() < 5 || placa.length() > 6 || !MOTO_PATTERN.matcher(placa).matches()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: La placa de moto debe tener 5 o 6 caracteres y cumplir con el estándar.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Use 0 para carro o 1 para moto.");
        }


        try {
            vehiculoDto.setPlaca(placa);
            VehiculoDto vehiculoGuardado = vhService.registrarVehiculo(vehiculoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoGuardado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{placa}")
    public ResponseEntity<?> editarVehiculo(
            @PathVariable String placa,
            @RequestBody VehiculoDto vehiculoDto) {
        try {
            VehiculoDto vehiculoActualizado = vhService.editarVehiculo(placa, vehiculoDto);
            return ResponseEntity.ok(vehiculoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{placa}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable String placa){

        try{
            vhService.eliminarVehiculo(placa);
            return ResponseEntity.ok("vehiculo con placa: " + placa + " ha sido correctamente eliminado");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: "+ e.getMessage());
        }
    }
}