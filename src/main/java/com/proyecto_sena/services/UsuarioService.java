package com.proyecto_sena.services;

import com.proyecto_sena.models.UsuarioModel;
import com.proyecto_sena.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Guarda un usuario nuevo si el correo y el nombre de usuario no están en uso.
     * @param usuario el usuario a registrar
     * @return el usuario guardado
     * @throws RuntimeException si ya existe el correo o el usuario
     */
    public UsuarioModel guardar(UsuarioModel usuario) {
        // Validación de unicidad de correo
        Optional<UsuarioModel> existenteCorreo = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existenteCorreo.isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        // Validación de unicidad de nombre de usuario
        Optional<UsuarioModel> existenteUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
        if (existenteUsuario.isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        return usuarioRepository.save(usuario);
    }

    public UsuarioModel obtenerPorCorreoYContrasena(String correo, String contrasena) {
        return usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
