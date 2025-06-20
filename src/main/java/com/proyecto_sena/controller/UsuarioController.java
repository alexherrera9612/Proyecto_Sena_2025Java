package com.proyecto_sena.controller;

import com.proyecto_sena.models.LoginRequest;
import com.proyecto_sena.models.UsuarioModel;
import com.proyecto_sena.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de usuarios.
 * Expone los endpoints para operaciones CRUD (crear, leer, actualizar y eliminar).
 */
 @RestController
@RequestMapping("/api/usuarios") // Ruta base para todos los métodos
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (CORS)

 public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // Servicio que maneja la lógica de negocio

    /**
     * Obtener todos los usuarios registrados.
     * Método: GET
     * URL: /api/usuarios
     */
    @GetMapping
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    /**
     * Obtener un usuario específico por su ID.
     * Método: GET
     * URL: /api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public Optional<UsuarioModel> obtenerUsuario(@PathVariable("id") Long id) {
        return usuarioService.obtenerPorId(id);
    }

    /**
     * Crear un nuevo usuario.
     * Método: POST
     * URL: /api/usuarios
     * Body: JSON con nombre, correo, usuario y contraseña
     */
    @PostMapping
    public UsuarioModel crearUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardar(usuario);
    }

    /**
     * Actualizar los datos de un usuario existente.
     * Método: PUT
     * URL: /api/usuarios/{id}
     * Body: JSON con datos actualizados
     */
    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioModel usuario) {
        usuario.setId(id); // Asegura que se actualice el usuario correcto
        return usuarioService.guardar(usuario);
    }

    @PostMapping("/login")
    public UsuarioModel login(@RequestBody LoginRequest loginRequest) {
        String correo = loginRequest.getCorreo();
        String contrasena = loginRequest.getContrasena();
        return usuarioService.obtenerPorCorreoYContrasena(correo, contrasena);
    }


    /**
     * Eliminar un usuario por su ID.
     * Método: DELETE
     * URL: /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable("id") Long id) {
        usuarioService.eliminar(id);
    }

}
