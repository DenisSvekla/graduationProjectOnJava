package com.tms.security;

import com.tms.model.User;
import com.tms.model.request.AuthRequest;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String  getToken (AuthRequest authRequest) {
        Optional<User> user = userRepository.findByName(authRequest.getLogin());
        if(user.isPresent() && passwordEncoder.matches(authRequest.getPassword(),user.get().getSurname())) {
            return jwtService.createJwtToken((authRequest.getLogin()));
        }
        return "";
    }
}
