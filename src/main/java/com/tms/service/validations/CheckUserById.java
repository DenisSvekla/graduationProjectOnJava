package com.tms.service.validations;


import java.util.Optional;

import com.tms.model.User;
import com.tms.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Сhecks that the received information is available to the user. Checked with SecurityContextHolder.
 */
@Component
public class CheckUserById {

    private final UserRepository userRepository;

    public CheckUserById(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Boolean checkUserByIdAndType(int id) {
        var userLoginFromSecurityContext = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userFromContext = userRepository.getUserByUserLogin(userLoginFromSecurityContext);
        User user = userRepository.findById(id).orElse(new User());
        if (user.getId() == userFromContext.get().getId() || userFromContext.get().getUser_type().equals("ADMIN")) {
            return true;
        }
        return false;
    }




}
