// Paquete al que pertenece este controlador
package com.proyecto_sena.controller;

// Importación de modelos y servicios necesarios
import com.proyecto_sena.models.LoginRequest;
import com.proyecto_sena.models.UsuarioModel;
import com.proyecto_sena.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Marca esta clase como un controlador REST
@RestController
// Define la ruta base para todas las peticiones a este controlador
@RequestMapping("/api/usuarios")
// Permite solicitudes desde cualquier origen (CORS)
@CrossOrigin(origins = "*")
public class UsuarioController {

    // Inyección del servicio que gestiona la lógica de usuarios
    @Autowired
    private UsuarioService usuarioService;

    // 1. Endpoint para listar todos los usuarios (GET /api/usuarios)
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        // Devuelve la lista de usuarios con código 200 OK
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // 2. Endpoint para obtener un usuario por ID (GET /api/usuarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("id") Long id) {
        // Busca el usuario por su ID
        Optional<UsuarioModel> usuario = usuarioService.obtenerPorId(id);
        // Si lo encuentra, lo devuelve; si no, devuelve un error 404
        return usuario.isPresent()
                ? ResponseEntity.ok(usuario.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Usuario no encontrado"));
    }

    // 3. Endpoint para crear un nuevo usuario (POST /api/usuarios)
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioModel usuario) {
        try {
            // Intenta guardar el nuevo usuario
            UsuarioModel creado = usuarioService.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado); // 201 Created
        } catch (DataIntegrityViolationException e) {
            // Si hay un conflicto por duplicidad de datos únicos (como correo o username)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "❌ El correo o nombre de usuario ya están registrados."));
        } catch (Exception e) {
            // Cualquier otro error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error al crear usuario."));
        }
    }

    // 4. Endpoint para actualizar un usuario existente (PUT /api/usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioModel usuario) {
        try {
            usuario.setId(id); // Asegura que el ID del usuario a actualizar sea el correcto
            UsuarioModel actualizado = usuarioService.guardar(usuario); // Actualiza el usuario
            return ResponseEntity.ok(actualizado); // Devuelve el usuario actualizado
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "❌ El correo o nombre de usuario ya están registrados."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error al actualizar usuario."));
        }
    }

    // 5. Endpoint para iniciar sesión (POST /api/usuarios/login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Obtiene los datos de inicio de sesión desde la petición
            String correo = loginRequest.getCorreo();
            String contrasena = loginRequest.getContrasena();

            // Busca el usuario con el correo y la contraseña
            UsuarioModel usuario = usuarioService.obtenerPorCorreoYContrasena(correo, contrasena);
            if (usuario != null) {
                return ResponseEntity.ok(usuario); // Login exitoso
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Usuario o contraseña incorrectos.")); // Credenciales incorrectas
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error en login.")); // Error inesperado
        }
    }

    // 6. Endpoint para eliminar un usuario (DELETE /api/usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Long id) {
        try {
            usuarioService.eliminar(id); // Elimina el usuario por ID
            return ResponseEntity.ok(Map.of("mensaje", "✅ Usuario eliminado")); // Confirma eliminación
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "❌ Error al eliminar usuario.")); // Error al eliminar
        }
    }
}
   
