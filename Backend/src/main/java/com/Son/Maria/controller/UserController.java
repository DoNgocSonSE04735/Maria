/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Son.Maria.controller;

import com.Son.Maria.dal.repository.UserRepository;
import com.Son.Maria.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roboc
 */
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUser(@RequestBody User user) {
        int userID;
        boolean userAlreadyExist;

        userID = user.getUserID();
        userAlreadyExist = userRepository.existsById(userID);
        if (userAlreadyExist == true) {
            throw new RuntimeException();
        } else {
            userRepository.save(user);
        }
    }

    @DeleteMapping("/users/{userID}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteUser(@PathVariable Integer userID) {
        boolean userAlreadyExist;

        userAlreadyExist = userRepository.existsById(userID);
        if (userAlreadyExist == true) {
            userRepository.deleteById(userID);
        } else {
            throw new RuntimeException();

        }
    }
}
