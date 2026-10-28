package com.devs.ParkingSalle.service;

import com.devs.ParkingSalle.model.Rol;

import java.util.List;

public interface IRolService {

    Rol registrarRol(Rol rol);
    Rol obtenerId(Integer idRol);
    List<Rol> listarRoles();
}
