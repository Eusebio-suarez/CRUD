package com.springBoot.CRUD.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.CRUD.dto.response.UsuarioActualizadoResponseDTO;
import com.springBoot.CRUD.dto.response.UsuarioEliminadoDTO;
import com.springBoot.CRUD.dto.response.UsuarioResponseDTO;
import com.springBoot.CRUD.dto.resquest.UsuarioRequestDTO;
import com.springBoot.CRUD.entity.Usuario;
import com.springBoot.CRUD.repository.UsuarioRepository;
import com.springBoot.utils.UsuarioException;

@Service//definir la clase como un servicio
public class UsuarioService {
    @Autowired//se crea automaticamente una instacia de la clase para evitar el uso del contructor manualmente
    private UsuarioRepository usuarioRepository;

    //ingresar un nuevo usuario
    public UsuarioResponseDTO registarUsuario(UsuarioRequestDTO usuarioRequestDTO){
        //usuario ingresado en la base de datos
        Usuario usuario = Usuario.builder()
                .nombre(usuarioRequestDTO.getNombre())
                .correo(usuarioRequestDTO.getCorreo())
                .contraseña(usuarioRequestDTO.getContraseña())
                .telefono(usuarioRequestDTO.getTelefono())
                .fechaCreaccion(LocalDateTime.now())
                .build();

        Usuario usuarioRegistrado = usuarioRepository.save(usuario);      

        //mapear el usuario a un dto
        return UsuarioResponseDTO.builder()
            .id(usuarioRegistrado.getId())
            .nombre(usuarioRegistrado.getNombre())
            .telefono(usuarioRegistrado.getTelefono())
            .correo(usuarioRegistrado.getCorreo())
            .fechacreacion(usuarioRegistrado.getFechaCreaccion())
            .build();
    } 

    //obtener todos los usuarios de la base de datos
    public List<UsuarioResponseDTO> obtenerUsuarios() {
        return usuarioRepository.findAll() // Obtiene todos los usuarios de la BD
                .stream() // Convierte la lista en un Stream para transformarla
                .map(usuario -> UsuarioResponseDTO.builder()
                 // Construye un DTO por cada usuario
                        .id(usuario.getId())
                        .nombre(usuario.getNombre())
                        .correo(usuario.getCorreo())
                        .telefono(usuario.getTelefono())
                        .fechacreacion(usuario.getFechaCreaccion())
                        .build())
                .collect(Collectors.toList()); 
                // Recolecta todos los DTOs en una lista
    }

    //actualizar un usuario
    public UsuarioActualizadoResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO){
        
        //mapear el usuariodto a un usuario pasando el id
        Usuario usuario = Usuario.builder()
            .id(id)
            .nombre(usuarioRequestDTO.getNombre())
            .correo(usuarioRequestDTO.getCorreo())
            .contraseña(usuarioRequestDTO.getContraseña())
            .telefono(usuarioRequestDTO.getTelefono())
            .build();
            
        //actualizar el usuario en la db    
        Usuario usuarioActualizado = usuarioRepository.save(usuario);  
        
        return UsuarioActualizadoResponseDTO.builder()
            .id(usuarioActualizado.getId())
            .nombre(usuarioActualizado.getNombre())
            .correo(usuarioActualizado.getCorreo())
            .telefono(usuarioActualizado.getTelefono())
            .fechaActualizacion(LocalDateTime.now())
            .build();
            
    }

    //metodo para eliminar un usuario por id
    public UsuarioEliminadoDTO eliminarUsuario(Long id){
        //buscar el usuario a eliminar
        Usuario usuarioEliminado = usuarioRepository.findById(id)
            .orElseThrow(()-> new UsuarioException("no se encontro el usuario con id: "+id));

        //eliminar el usuario d ela db
        usuarioRepository.deleteById(id);
        
        return UsuarioEliminadoDTO.builder()
                .id(usuarioEliminado.getId())
                .correo(usuarioEliminado.getCorreo())
                .build();
    
    }
}
