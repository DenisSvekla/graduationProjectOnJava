package com.tms.mapper;

import com.tms.model.domain.User;
import com.tms.model.response.user.UserGetByIdResponse;
import org.springframework.stereotype.Component;

/**
 * Class for change object from user  to user dto
 */
@Component
public class MappersFromUser {

    public UserGetByIdResponse userGetByIdResponseFromUser(UserGetByIdResponse userGetByIdResponse, User user) {
        userGetByIdResponse.setName(user.getName());
        userGetByIdResponse.setLoginUser(user.getLoginUser());
        userGetByIdResponse.setSurname(user.getSurname());
        userGetByIdResponse.setUser_type(user.getUserType());
        userGetByIdResponse.setEmail(user.getEmail());
        userGetByIdResponse.setBirthday_date(user.getBirthdayDate());
        return userGetByIdResponse;
    }
}
