package com.tms.service;

import com.tms.model.User;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAllUsers() {    return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User createUser(User user) { return userRepository.save(user);}

   // public Boolean createUser(User user, Security security) {return userRepository.createUser(user,security);}


}
