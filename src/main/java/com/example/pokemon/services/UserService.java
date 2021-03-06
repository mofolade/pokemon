package com.example.pokemon.services;

import com.example.pokemon.entities.User;
import com.example.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(String username) {
        if(username != null) {
            var user = userRepository.findByUsername(username).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by username %s.", username)));
            return List.of(user);
        }
        return userRepository.findAll();
    }

    public List<User> findByUserNameRegex(String name) {
        var users = userRepository.findByNameRegex(name);
        return users;
    }

    public List<User> findByUserAttributes(String name,String city,String username,String reg_year) {
        var users = userRepository.findByAttributes(name,city,username,reg_year);
        return users;
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    public User save(User user) {
        /*if(StringUtils.isEmpty(user.getPassword())) { // user.getPassword() == null || user.getPassword().isEmpty()
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I need a password!!");
        }*/
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void update(String id, User user) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id));
        }
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(String id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id));
        }
        userRepository.deleteById(id);
    }
}
