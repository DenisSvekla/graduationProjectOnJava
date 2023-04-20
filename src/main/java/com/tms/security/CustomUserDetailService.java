package com.tms.security;

import com.tms.model.User;
import com.tms.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService
{
    private final UserRepository userRepository;


    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(()-> new UsernameNotFoundException(username));
        UserDetails securityUser = org.springframework.security.core.userdetails.User
                .builder()
                .username((user.getName()))
                .password(user.getSurname())
                .roles(user.getUser_type())
                .build();
        return securityUser;

    }
}
