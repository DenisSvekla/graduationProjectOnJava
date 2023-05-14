package com.tms.security.config;

import com.tms.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtFilter jwtFilter;


    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf().disable()
                        .httpBasic().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/auth").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/registration").permitAll()
                        .antMatchers(HttpMethod.GET, "/car").permitAll()
                        .antMatchers(HttpMethod.GET, "/car/**").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/car/**").permitAll()
                        .antMatchers(HttpMethod.PUT, "/car").permitAll()
                        .antMatchers(HttpMethod.POST, "/car").permitAll()
                        .antMatchers(HttpMethod.PUT, "/car/**/addComment").permitAll()
                        .antMatchers(HttpMethod.GET, "/user/**/phone").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/**/phone").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/user/**/phone/**").permitAll()
                        .antMatchers(HttpMethod.PUT, "/car/**/deleteCar").permitAll()
                        .antMatchers(HttpMethod.PUT, "/car/**/addCar").permitAll()
                        .antMatchers(HttpMethod.POST, "/car/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/user").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                        .and()
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth"))
                        .and()
                        .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
