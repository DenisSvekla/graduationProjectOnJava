package com.tms.utils;

import java.util.Optional;

import com.tms.model.domain.User;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * get info about users from context
 */
@Component
public class GetIdUserFromContext {

    private final UserRepository userRepository;

    @Autowired
    public GetIdUserFromContext(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * get user id from context
     *
     * @return
     */
    public int getIdUserFromContext() {
        var userLoginFromSecurityContext = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userFromContext = userRepository.getUserByUserLogin(userLoginFromSecurityContext);
        return userFromContext.get().getId();
    }
}
