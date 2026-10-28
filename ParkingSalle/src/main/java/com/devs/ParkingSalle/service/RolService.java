package com.devs.ParkingSalle.service;

import com.devs.ParkingSalle.model.Rol;
import com.devs.ParkingSalle.repository.IRolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService{


    private final IRolRepository rlRepo;

    public RolService(IRolRepository rlRepo) {
        this.rlRepo = rlRepo;
    }

    @Override
    public Rol registrarRol(Rol rol) {
        if (rlRepo.findByNombreRol(rol.getNombreRol()).isPresent()) {
            throw new RuntimeException("El rol ya existe: ");
        }
        return rlRepo.save(rol);
    }

    @Override
    public Rol obtenerId(Integer idRol) {
        return rlRepo.findById(idRol).orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + idRol));
    }

    @Override
    public List<Rol> listarRoles() {
        return rlRepo.findAll();
    }
}
