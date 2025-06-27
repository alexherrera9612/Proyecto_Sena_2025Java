package com.proyecto_sena.repositories;

import com.proyecto_sena.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // Buscar por correo único
    Optional<UsuarioModel> findByCorreo(String correo);

    // Buscar por nombre de usuario único
    Optional<UsuarioModel> findByUsuario(String usuario);

    // Login por correo y contraseña (solo uno debería coincidir)
    UsuarioModel findByCorreoAndContrasena(String correo, String contrasena);

    // Login por nombre de usuario y contraseña (si lo deseas usar también)
    UsuarioModel findByUsuarioAndContrasena(String usuario, String contrasena);
}
