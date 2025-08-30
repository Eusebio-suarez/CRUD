package com.springBoot.CRUD.dto;

import lombok.Builder;
import lombok.Data;

@Data//geeter y setters desde lombuk
@Builder//contructor automatico con lombuk
public class UsuarioDTO {
    private String nombre;
    private String correo;
    private String telefono;
}
