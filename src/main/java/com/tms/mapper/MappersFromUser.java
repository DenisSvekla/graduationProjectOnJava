package com.tms.mapper;

import com.tms.model.domain.User;
import com.tms.model.response.User.UserGetByIdResponse;
import org.springframework.stereotype.Component;

@Component
public class MappersFromUser {

    public UserGetByIdResponse userGetByIdResponseFromUser (UserGetByIdResponse userGetByIdResponse, User user) {
        userGetByIdResponse.setName(user.getName());
        userGetByIdResponse.setLoginUser(user.getLoginUser());
        userGetByIdResponse.setSurname(user.getSurname());
        userGetByIdResponse.setUser_type(user.getUser_type());
        userGetByIdResponse.setEmail(user.getEmail());
        userGetByIdResponse.setBirthday_date(user.getBirthday_date());
        return userGetByIdResponse;
    }
}
