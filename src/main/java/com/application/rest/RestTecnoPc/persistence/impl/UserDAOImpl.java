package com.application.rest.RestTecnoPc.persistence.impl;

import com.application.rest.RestTecnoPc.entities.User;
import com.application.rest.RestTecnoPc.persistence.IUserDAO;
import com.application.rest.RestTecnoPc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserDAOImpl implements IUserDAO {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public Optional<User> findByEmailAndContrasena(String email, String contrasena) {
        return (userRepository.findByEmailAndContrasena(email, contrasena));
    }
    @Override
    public List<User> findByTelefono(String telefono) {
        return userRepository.findByTelefono(telefono);
    }
}
