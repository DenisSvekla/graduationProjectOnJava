package com.tms.service;

import java.sql.Date;
import java.util.ArrayList;

import com.tms.model.User;
import com.tms.model.request.User.UserRegistrationRequest;
import com.tms.model.response.User.UserGetByIdResponse;
import com.tms.repository.UserRepository;
import com.tms.service.validations.CheckUserById;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final String userType = "USER";
    private final PasswordEncoder passwordEncoder;

    private final CheckUserById checkUserById;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CheckUserById checkUserById) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.checkUserById = checkUserById;
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public  UserGetByIdResponse getUserById (int id) {
        UserGetByIdResponse userGetByIdResponse = new UserGetByIdResponse();
        User user = userRepository.findByIs_deleted(id).orElse(new User());
        if (checkUserById.checkUserByIdAndType(id) && !user.is_deleted()) {
            userGetByIdResponse.setName(user.getName());
            userGetByIdResponse.setLoginUser(user.getLoginUser());
            userGetByIdResponse.setSurname(user.getSurname());
            userGetByIdResponse.setUser_type(user.getUser_type());
            userGetByIdResponse.setEmail(user.getEmail());
            return userGetByIdResponse;
        }
        return userGetByIdResponse=null;
    }
    public User getUserByUserLogin(String userLogin){
        return userRepository.findByLoginUser(userLogin).orElse((new User()));
    }


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

@Transactional
    public Boolean deleteUser(int id) {
        if (checkUserById.checkUserByIdAndType(id)){
            userRepository.deleteUser(id);
            return true;
        }
       return false;
    }

//    public User updateUser(UserUpdateDto userUpdateDto, int id) {
//        Optional<User> userFromDB = userRepository.findByIs_deleted(id);
//        if (checkUserById.checkUserByIdAndType(id)){
//            userFromDB.get().setLoginUser(userUpdateDto.getLoginUser());
//            userFromDB.get().setName(userUpdateDto.getName());
//            userFromDB.get().setSurname(userUpdateDto.getSurname());
//            userFromDB.get().setEmail(userUpdateDto.getEmail());
//            userFromDB.get().setBirthday_date(userUpdateDto.getBirthday_date());
//          //  user.setPasswordUser(passwordEncoder.encode(userUpdateDto.getPasswordUser()));
//            userFromDB.get().setChanged(new Date(System.currentTimeMillis()));
//            return userRepository.saveAndFlush(userFromDB);
//        }
//        return user = null;
//    }

   // public Boolean createUser(User user, Security security) {return userRepository.createUser(user,security);}


}
