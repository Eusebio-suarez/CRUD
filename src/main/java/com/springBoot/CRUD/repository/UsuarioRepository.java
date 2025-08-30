package com.springBoot.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.CRUD.entity.Usuario;

public interface  UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
