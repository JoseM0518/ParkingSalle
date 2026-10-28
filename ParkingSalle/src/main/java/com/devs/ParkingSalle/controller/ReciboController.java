package com.devs.ParkingSalle.controller;

import com.devs.ParkingSalle.dto.ReciboDto;
import com.devs.ParkingSalle.service.IReciboService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/recibos")
@CrossOrigin(origins = "*")
public class ReciboController {


    private final IReciboService rcServ;

    public ReciboController(IReciboService rcServ) {
        this.rcServ = rcServ;
    }

    @GetMapping
    public ResponseEntity<List<ReciboDto>> listarRecibos() {
        return ResponseEntity.ok(rcServ.listarRecibos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReciboDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rcServ.obtenerReciboPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReciboDto> generarRecibo(
            @RequestParam String placa,
            @RequestParam BigDecimal valorNeto) {
        ReciboDto nuevoRecibo = rcServ.generarRecibo(placa, valorNeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecibo);
    }
}