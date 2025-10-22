package com.application.rest.RestTecnoPc.repository;

import com.application.rest.RestTecnoPc.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE LOWER(CONCAT(:email, '%'))")
    List<User> findByEmail(@Param("email") String email);
    Optional<User> findByEmailAndContrasena(String email, String contrasena);
    @Query("SELECT u FROM User u WHERE u.telefono LIKE CONCAT(:phone, '%')")
    List<User> findByTelefono(@Param("phone") String telefono);

}
