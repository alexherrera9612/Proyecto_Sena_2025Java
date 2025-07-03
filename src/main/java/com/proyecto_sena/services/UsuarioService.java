// Paquete al que pertenece este servicio
package com.proyecto_sena.services;

// Importación de clases necesarias
import com.proyecto_sena.models.UsuarioModel;
import com.proyecto_sena.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Marca esta clase como un componente de servicio de Spring
@Service
public class UsuarioService {

    // Inyección automática del repositorio de usuarios
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para obtener todos los usuarios de la base de datos
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll(); // Ejecuta SELECT * FROM usuarios
    }

    // Método para obtener un usuario por su ID
    public Optional<UsuarioModel> obtenerPorId(Long id) {
        return usuarioRepository.findById(id); // Ejecuta SELECT * FROM usuarios WHERE id = ?
    }

    /**
     * Guarda un nuevo usuario si el correo y el nombre de usuario no están repetidos.
     * @param usuario El usuario a registrar
     * @return El usuario guardado en la base de datos
     * @throws RuntimeException Si ya existe un usuario con el mismo correo o nombre de usuario
     */
    public UsuarioModel guardar(UsuarioModel usuario) {
        // Verifica si ya existe un usuario con el mismo correo
        Optional<UsuarioModel> existenteCorreo = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existenteCorreo.isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        // Verifica si ya existe un usuario con el mismo nombre de usuario
        Optional<UsuarioModel> existenteUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
        if (existenteUsuario.isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        // Guarda el usuario si pasa las validaciones anteriores
        return usuarioRepository.save(usuario);
    }

    // Busca un usuario por correo y contraseña (para login)
    public UsuarioModel obtenerPorCorreoYContrasena(String correo, String contrasena) {
        return usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    // Elimina un usuario por su ID
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id); // Ejecuta DELETE FROM usuarios WHERE id = ?
    }
    
}
// Este servicio UsuarioService se encarga de la lógica de negocio relacionada con los usuarios.
// Utiliza el repositorio UsuarioRepository para interactuar con la base de datos.
// Proporciona métodos para listar usuarios, obtener por ID, guardar nuevos usuarios y eliminar usuarios.
// También incluye validaciones para evitar duplicados de correo y nombre de usuario al crear un nuevo usuario.