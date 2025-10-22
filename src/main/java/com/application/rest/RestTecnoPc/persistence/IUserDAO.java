package com.application.rest.RestTecnoPc.persistence;

import com.application.rest.RestTecnoPc.entities.User; 
import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> findById(Long id);
    List<User> findAll();

    void save (User user);
    void deleteById(Long id);
    List<User> findByEmail(String email);
    Optional<User> findByEmailAndContrasena(String email, String contrasena);
    List<User> findByTelefono(String telefono);

}
