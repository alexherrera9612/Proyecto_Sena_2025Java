package com.proyecto_sena.controllers;

import com.proyecto_sena.models.UsuarioModel;
import com.proyecto_sena.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioModel> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping
    public UsuarioModel crearUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        usuario.setId(id);
        return usuarioService.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
