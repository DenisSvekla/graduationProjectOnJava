package com.tms.service;

import java.util.ArrayList;

import com.tms.model.domain.User;
import com.tms.model.request.user.UserRegistrationRequest;
import com.tms.model.request.user.UserUpdateDto;
import com.tms.model.response.user.UserGetByIdResponse;

public interface UserService {

    ArrayList<User> getAllUsers();

    UserGetByIdResponse getUserById(int id);

    User userRegistration(UserRegistrationRequest userRegistrationRequest);

    void deleteUser(int id);

    User updateUser(UserUpdateDto userUpdateDto, int id);

}
