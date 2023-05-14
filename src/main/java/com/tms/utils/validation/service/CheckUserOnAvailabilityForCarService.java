package com.tms.utils.validation.service;

import java.util.ArrayList;
import java.util.Optional;

import com.tms.ExceprtionResolver.NotFoundException;
import com.tms.model.domain.Car;
import com.tms.model.domain.User;
import com.tms.repository.CarRepository;
import com.tms.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CheckUserOnAvailabilityForCarService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public CheckUserOnAvailabilityForCarService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public Boolean checkUserOnAvailabilityForCarServiceByCarId(int id) {
        var userLoginFromSecurityContext = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userFromContext = userRepository.getUserByUserLogin(userLoginFromSecurityContext);
        ArrayList<Car> car = carRepository.getCarsByUserId(userFromContext.get().getId()).orElseThrow(()->new NotFoundException("asd"));
        if (car.size()!=0 || userFromContext.get().getUser_type().equals("ADMIN")) {
            return true;
        }
        return false;
    }

}
