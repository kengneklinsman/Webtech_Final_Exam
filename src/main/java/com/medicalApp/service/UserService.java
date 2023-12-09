package com.medicalApp.service;

import com.medicalApp.modal.User;
import com.medicalApp.serviceImplementation.UserNotFoundException;

import java.util.List;


public interface UserService {
    User saveUser(User user);

    User updateUser(User user);


    User findUserByUsername(String username);
    boolean usernameExists(String username);
    List<User> displayUsers();
    User findUserById(Long user) throws UserNotFoundException;
    User updateUser(Long user);
    void deleteUser(Long user) throws UserNotFoundException;

}
