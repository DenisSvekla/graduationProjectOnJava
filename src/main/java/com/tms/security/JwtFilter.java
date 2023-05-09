package com.tms.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.tms.model.User;
import com.tms.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtFilter extends GenericFilterBean {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public JwtFilter(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(token) && token.startsWith("Bearer ") && jwtService.isValid(token.substring(7))) {
            String login = jwtService.getLoginFromToken(token.substring(7));
            User userFromDb = userRepository.findByLoginUser(login).orElseThrow(() -> new UsernameNotFoundException(login));

            UserDetails secUSer = org.springframework.security.core.userdetails.User.builder()
                    .username(userFromDb.getLoginUser())
                    .password(userFromDb.getPasswordUser())
                    .roles(userFromDb.getUser_type())
                    .build();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(secUSer, null, secUSer.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        chain.doFilter(request, response);
    }
}
