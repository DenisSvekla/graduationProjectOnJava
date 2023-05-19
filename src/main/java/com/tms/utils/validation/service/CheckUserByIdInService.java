package com.tms.utils.validation.service;


import java.util.Optional;

import com.tms.model.domain.User;
import com.tms.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Сheck that the received information is available to the user. Checked with SecurityContextHolder.
 */
@Component
public class CheckUserByIdInService {

    private final UserRepository userRepository;

    public CheckUserByIdInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Boolean checkUserByIdAndType(int id) {
        var userLoginFromSecurityContext = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userFromContext = userRepository.getUserByUserLogin(userLoginFromSecurityContext);
        User user = userRepository.findById(id).orElse(new User());
        if (user.getId() == userFromContext.get().getId() || userFromContext.get().getUserType().equals("ADMIN")) {
            return true;
        }
        return false;
    }

    public Boolean checkUserByType() {
        var userLoginFromSecurityContext = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userFromContext = userRepository.getUserByUserLogin(userLoginFromSecurityContext);
        if (userFromContext.get().getUserType().equals("ADMIN")) {
            return true;
        }
        return false;
    }


}
