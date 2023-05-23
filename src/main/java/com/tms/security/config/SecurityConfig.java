package com.tms.security.config;

import com.tms.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(AUTH_WHITELIST);
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
                        .antMatchers(HttpMethod.POST, "/actuator").permitAll()
                        .antMatchers(HttpMethod.POST, "/car").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/car").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/car").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/car/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/car/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/car/**/addComment").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/user/**/phone").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/user/**/subscription").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/user/**/subscription").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/user/**/phone").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/user/**/phone/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/car/**/deleteCar").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/car/**/addCar").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/car/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers("/swagger-ui/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/user/**/subscription").hasRole("ADMIN")
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
