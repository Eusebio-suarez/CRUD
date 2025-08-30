package com.springBoot.CRUD.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.CRUD.dto.UsuarioDTO;
import com.springBoot.CRUD.repository.UsuarioRepository;

@Service//definir la clase como un servicio
public class UsuarioService {
    @Autowired//se crea automaticamente una instacia de la clase para evitar el uso del contructor manualmente
    private UsuarioRepository usuarioRepository;

    //servicio para obtener todos los usuarios de la base de datos
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioRepository.findAll() // Obtiene todos los usuarios de la BD
                .stream() // Convierte la lista en un Stream para transformarla
                .map(usuario -> UsuarioDTO.builder() // Construye un DTO por cada usuario
                        .nombre(usuario.getNombre())
                        .correo(usuario.getCorreo())
                        .telefono(usuario.getTelefono())
                        .build())
                .collect(Collectors.toList()); // Recolecta todos los DTOs en una lista
    }
}
