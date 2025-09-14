package com.springBoot.CRUD.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//definir que es una entidad
@Table(name="usuarios") // nombre de la tabla en la base de datos
@Data //getter y setters cn lombuk
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id//id clave primaria de la entidad
    @GeneratedValue(strategy= GenerationType.IDENTITY)//la base de datos genera el id
    private Long id;
    
    @Column(name="nombre")//nombre de la columna en la base de datos
    @NotBlank//valida que el campo no este vacio
    //define un rango en el cual debe estar el campo y tambien un mensaje en caso de error
    @Size(min=3,max=20, message="el nombre debe tebner minimo 3 y maximo 20 caracteres")
    private String nombre;

    @Column(name="correo",unique=true)
    @Email//valida que tenga un formato de correo
    @NotBlank//valida que el campo no este vacio
    private String correo;

    @Column(name="contraseña")
    @NotBlank//valida que el campo no este vacio
    //define un rango en el cual debe estar el campo y tambien un mensaje en caso de error
    @Size(min=8,max=20, message="la contraeña debe tener minimo 8 y maximo 20 caracteres")
    private String contraseña;

    @Column(name="telefono")
    @NotBlank//valida que el campo no este vacio
    //define un rango en el cual debe estar el campo y tambien un mensaje en caso de error
    @Size(min=5,max=20, message="el telefono debe tener minimo 5 maximo 20 caracteres")
    private String telefono;

    @Column(name="fecha_creacion",updatable=false)//cuando se actualiza el registro este campo mantien su valor
    private LocalDateTime fechaCreaccion;

    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
