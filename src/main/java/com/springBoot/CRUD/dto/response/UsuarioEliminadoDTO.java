package com.springBoot.CRUD.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioEliminadoDTO {
    private Long id;
    private String correo;
}
