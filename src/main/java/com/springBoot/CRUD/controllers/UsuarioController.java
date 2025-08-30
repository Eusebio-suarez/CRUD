package com.springBoot.CRUD.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.CRUD.dto.UsuarioDTO;
import com.springBoot.CRUD.services.UsuarioService;

@RestController // definir esta calse como un controlador
@RequestMapping("/usuarios") //ruta inicial del controlador

public class UsuarioController {

    @Autowired // se crea automaticamente una instacia evitando usar el contructor directamente
    //instacia del servicio para oder usar sus metodos
    private UsuarioService usuarioService;

    //endpoint para obtener todos los usuarios
    @GetMapping("")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){

        //traer los usuarios desde el servicio
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuarios();

        //retornar un codigo de stado de ok (200) con los usuarios
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarios);
    }

}
