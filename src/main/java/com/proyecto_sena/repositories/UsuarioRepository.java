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
// Este repositorio UsuarioRepository extiende JpaRepository para proporcionar acceso a la base de datos.
// Incluye métodos para buscar usuarios por correo y nombre de usuario, así como para realizar el login.
// Utiliza Optional para manejar casos donde no se encuentre un usuario con el correo o nombre de usuario especificado.
// También incluye métodos para buscar por correo y contraseña, y por nombre de usuario y contraseña, para el proceso de inicio de sesión.