package com.proyecto_sena.repositories;

import com.proyecto_sena.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // Buscar por correo único
    UsuarioModel findByCorreo(String correo);

    // Login por correo y contraseña
    UsuarioModel findByCorreoAndContrasena(String correo, String contrasena);

    // Login por nombre de usuario y contraseña
    UsuarioModel findByUsuarioAndContrasena(String usuario, String contrasena);
}
