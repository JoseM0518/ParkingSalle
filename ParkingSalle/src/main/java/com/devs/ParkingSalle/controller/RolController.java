package com.devs.ParkingSalle.controller;

import com.devs.ParkingSalle.dto.VehiculoDto;
import com.devs.ParkingSalle.model.Rol;
import com.devs.ParkingSalle.service.IRolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {


    private final IRolService rolService;

    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Rol rolEncontrado= rolService.obtenerId(id);
            return ResponseEntity.ok(rolEncontrado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Rol> registrarRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.registrarRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }
}