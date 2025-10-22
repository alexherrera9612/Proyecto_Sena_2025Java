package com.application.rest.RestTecnoPc.service.impl;

import com.application.rest.RestTecnoPc.entities.User;
import com.application.rest.RestTecnoPc.persistence.IUserDAO;
import com.application.rest.RestTecnoPc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDAO userDAO;
    
    @Override
    public Optional<User> findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }
    @Override
    public Optional<User> findByEmailAndContrasena(String correo, String contrasena) {
        return userDAO.findByEmailAndContrasena(correo, contrasena);
    }
    @Override
    public List<User> findByTelefono(String telefono) {
        return userDAO.findByTelefono(telefono);
    }
    
}
