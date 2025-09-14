package com.springBoot.CRUD.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data//geeter y setters desde lombuk
@Builder//contructor automatico con lombuk
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private LocalDateTime fechacreacion;
}
