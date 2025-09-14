package com.springBoot.CRUD.dto.resquest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {
    @NotBlank(message="el nombre es obligatorio")
    private String nombre;

    @NotBlank(message="el correo es obligatorio")
    private String correo;

    @NotBlank(message="la contraseña es obligatoria")
    private String contraseña;

    @NotBlank(message="el telefono es obligatorio")
    private String telefono;
}
