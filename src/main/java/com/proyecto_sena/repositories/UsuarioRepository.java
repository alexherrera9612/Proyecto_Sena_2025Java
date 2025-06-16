package com.proyecto_sena.repositories;

import com.proyecto_sena.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    UsuarioModel findByCorreo(String correo); // para login futuro
}
