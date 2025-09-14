package com.springBoot.CRUD.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.CRUD.dto.response.UsuarioResponseDTO;
import com.springBoot.CRUD.dto.resquest.UsuarioRequestDTO;
import com.springBoot.CRUD.services.UsuarioService;
import com.springBoot.utils.ApiResponse;
import com.springBoot.utils.UsuarioException;

import jakarta.validation.Valid;

@RestController // definir esta calse como un controlador
@RequestMapping("/usuarios") //ruta inicial del controlador

public class UsuarioController {

    @Autowired // se crea automaticamente una instacia evitando usar el contructor directamente
    //instacia del servicio para oder usar sus metodos
    private UsuarioService usuarioService;

    //endpoint para obtener todos los usuarios
    @GetMapping("")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios(){

        //traer los usuarios desde el servicio
        List<UsuarioResponseDTO> usuarios = usuarioService.obtenerUsuarios();

        //retornar un codigo de stado de ok (200) con los usuarios
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarios);
    }

    //endpoint para registar un usuario
    @PostMapping("/registar")
    public ResponseEntity<ApiResponse<?>> registarUsuraio(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        try{
            UsuarioResponseDTO usuario = usuarioService.registarUsuario(usuarioRequestDTO);

            if(usuario !=null){

                return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                    .ok(true)
                    .mensaje("usuario creado correctamente")
                    .data(usuario)
                    .build()
                );
            }
            else{
                throw new UsuarioException("no se pudo registar el usuario");
            }
        }
        catch(UsuarioException e){
            //capturar la excepcion de usuario
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                    .ok(false)
                    .mensaje("Errror de validacion: "+e.getMessage())
                    .data(null)
                    .build()
                );
        }
        catch(Exception e){
            //capturar el error inesperado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                    .ok(false)
                    .mensaje("error inesperado: "+ e.getMessage())
                    .data(null)
                    .build()
                );
        }
    }

}
