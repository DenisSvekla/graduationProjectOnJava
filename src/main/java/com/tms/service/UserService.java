package com.tms.service;

import java.sql.Date;
import java.util.ArrayList;

import com.tms.model.User;
import com.tms.model.request.User.UserRegistrationRequest;
import com.tms.model.response.User.UserGetByIdResponse;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final String userType = "USER";
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ArrayList<User> getAllUsers() { return (ArrayList<User>) userRepository.findAll();
    }

    public UserGetByIdResponse getUserById(int id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User getUserByUserLogin(String userLogin){
        return userRepository.findByLoginUser(userLogin).orElse((new User()));
    }

    public User createUser(User user) { return userRepository.save(user);}

    @Transactional
    public Boolean userRegistration(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setLoginUser(userRegistrationRequest.getLoginUser());
        user.setPasswordUser(passwordEncoder.encode(userRegistrationRequest.getPasswordUser()));
        user.setName(userRegistrationRequest.getName());
        user.setSurname(userRegistrationRequest.getSurname());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setBirthday_date(userRegistrationRequest.getBirthday_date());
        user.setUser_type(userType);
        user.setCreated(new Date(System.currentTimeMillis()));
        user.setChanged(new Date(System.currentTimeMillis()));
        user.set_deleted(false);
        return userRepository.save(user) !=null;
    }

   // public Boolean createUser(User user, Security security) {return userRepository.createUser(user,security);}


}
