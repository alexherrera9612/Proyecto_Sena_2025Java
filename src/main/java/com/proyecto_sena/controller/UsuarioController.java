package com.proyecto_sena.controller;

import com.proyecto_sena.models.LoginRequest;
import com.proyecto_sena.models.UsuarioModel;
import com.proyecto_sena.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // 2. Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("id") Long id) {
        Optional<UsuarioModel> usuario = usuarioService.obtenerPorId(id);
        return usuario.isPresent()
                ? ResponseEntity.ok(usuario.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Usuario no encontrado"));
    }

    // 3. Crear nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioModel usuario) {
        try {
            UsuarioModel creado = usuarioService.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "❌ El correo o nombre de usuario ya están registrados."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ El correo o nombre de usuario ya están registrados."));
        }
    }

    // 4. Actualizar usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioModel usuario) {
        try {
            usuario.setId(id);
            UsuarioModel actualizado = usuarioService.guardar(usuario);
            return ResponseEntity.ok(actualizado);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "❌ El correo o nombre de usuario ya están registrados."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error al actualizar usuario."));
        }
    }

    // 5. Iniciar sesión (Login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String correo = loginRequest.getCorreo();
            String contrasena = loginRequest.getContrasena();

            UsuarioModel usuario = usuarioService.obtenerPorCorreoYContrasena(correo, contrasena);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Usuario o contraseña incorrectos."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error en login."));
        }
    }

    // 6. Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "✅ Usuario eliminado"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error al eliminar usuario."));
        }
    }
}
