package com.springBoot.CRUD.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.CRUD.dto.response.UsuarioActualizadoResponseDTO;
import com.springBoot.CRUD.dto.response.UsuarioResponseDTO;
import com.springBoot.CRUD.dto.resquest.UsuarioRequestDTO;
import com.springBoot.CRUD.repository.UsuarioRepository;
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

    //endpoint para actualizar un usuario
    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<?>> actualizarUsuario(@Valid @RequestParam Long id,@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        try{
            //actualizar el usuario 
            UsuarioActualizadoResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioRequestDTO);
            System.out.println(usuarioRequestDTO);

            //validar que el usuario se haya creado correctamente
            if(usuarioActualizado !=null){
                return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.builder()
                        .ok(true)
                        .mensaje("usuario actualizado con exito")
                        .data(usuarioActualizado)
                        .build()
                    );
            }
            else{
                throw new UsuarioException("error al actualizar el usuario");
            }

        }

        //capturar excepciones del usuario
        catch(UsuarioException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                    .ok(false)
                    .mensaje("no se pudo actualizar el usuario :"+e.getMessage())
                    .data(null)
                    .build()
                );
        }
        //capturar la excepciones inesperadas
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                    .ok(false)
                    .mensaje("error inesperado :"+e.getMessage())
                    .data(null)
                    .build()
                );
        }
    }

}
