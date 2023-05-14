package com.tms.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import com.tms.ExceprtionResolver.NotFoundException;
import com.tms.ExceprtionResolver.OtherException;
import com.tms.mapper.MappersFromUser;
import com.tms.mapper.MappersToUser;
import com.tms.model.domain.User;
import com.tms.model.request.User.UserRegistrationRequest;
import com.tms.model.request.User.UserUpdateDto;
import com.tms.model.response.User.UserGetByIdResponse;
import com.tms.repository.UserRepository;
import com.tms.service.UserService;
import com.tms.utils.validation.service.CheckUserByIdInUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tms.utils.ExceptionMessage.*;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final String userType = "USER";

    private final CheckUserByIdInUserService checkUserByIdInUserService;

    private final MappersToUser mappersToUser;
    private final MappersFromUser mappersFromUser;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,  CheckUserByIdInUserService checkUserByIdInUserService, MappersToUser mappersToUser, MappersFromUser mappersFromUser) {
        this.userRepository = userRepository;
        this.checkUserByIdInUserService = checkUserByIdInUserService;
        this.mappersToUser = mappersToUser;
        this.mappersFromUser = mappersFromUser;
    }

    public ArrayList<User> getAllUsers() {
        if (checkUserByIdInUserService.checkUserByType()){
            return (ArrayList<User>) userRepository.findAll();
        }
        throw new OtherException(NO_ACCESS);
    }

    public UserGetByIdResponse getUserById(int id) {
        UserGetByIdResponse userGetByIdResponse = new UserGetByIdResponse();
        User user = userRepository.findByIs_deleted(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if (checkUserByIdInUserService.checkUserByIdAndType(id) && !user.is_deleted()) {
            mappersFromUser.userGetByIdResponseFromUser(userGetByIdResponse,user);
            return userGetByIdResponse;
        }
        throw new OtherException(NO_ACCESS);
    }



    @Transactional
    public User userRegistration(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        mappersToUser.fromUserRegistrationRequestToUser(userRegistrationRequest,user);
        user.setUser_type(userType);
        user.setCreated(new Date(System.currentTimeMillis()));
        user.setChanged(new Date(System.currentTimeMillis()));
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUser(int id) {
        int result = 0;
        if (checkUserByIdInUserService.checkUserByIdAndType(id)) {
           result = userRepository.deleteUser(id);
        }
        if(result==0){
            throw new OtherException(DELETE_USER_EXCEPTION);
        }
    }

    public User updateUser(UserUpdateDto userUpdateDto, int id) {
        User userFromDB = userRepository.findByIs_deleted(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        if (checkUserByIdInUserService.checkUserByIdAndType(id)){
            mappersToUser.fromUserUpdateDtoToUser(userUpdateDto, userFromDB);
            userFromDB.setChanged(new Date(System.currentTimeMillis()));
            return userRepository.saveAndFlush(userFromDB);
        }
        throw new OtherException(NO_ACCESS);
    }



}
