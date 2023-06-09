package com.tms.mapper;

import com.tms.model.domain.User;
import com.tms.model.request.user.UserRegistrationRequest;
import com.tms.model.request.user.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class for change object from user dto  to user
 */
@Component
public class MappersToUser {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MappersToUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromUserUpdateDtoToUser(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.getLoginUser() != null) {
            user.setLoginUser(userUpdateDto.getLoginUser());
        }
        if (userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }
        if (userUpdateDto.getName() != null) {
            user.setSurname(userUpdateDto.getSurname());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getBirthdayDate() != null) {
            user.setBirthdayDate(userUpdateDto.getBirthdayDate());
        }
        return user;
    }

    public User fromUserRegistrationRequestToUser(UserRegistrationRequest userRegistrationRequest, User user) {
        user.setLoginUser(userRegistrationRequest.getLoginUser());
        user.setPasswordUser(passwordEncoder.encode(userRegistrationRequest.getPasswordUser()));
        user.setName(userRegistrationRequest.getName());
        user.setSurname(userRegistrationRequest.getSurname());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setBirthdayDate(userRegistrationRequest.getBirthdayDate());
        return user;
    }
}
