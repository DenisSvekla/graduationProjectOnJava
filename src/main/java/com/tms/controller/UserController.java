package com.tms.controller;

import java.util.ArrayList;

import com.tms.model.User;
import com.tms.model.request.User.UserRegistrationRequest;
import com.tms.model.response.User.UserGetByIdResponse;
import com.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
   public ResponseEntity<ArrayList<User>> getAllUser() {
        ArrayList<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list,(!list.isEmpty())?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    // TODO добавить получение всех пользователей с помощью dto объекта
    // TODO добавить получение пользователя по id
    // TODO добавить добавление авто в избранное
    // TODO добавить обновление пользователя
    // TODO добавить удаление пользователя
    // TODO добавить добавление пользователя
    @Operation(summary = "Отдает пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер"),
            @ApiResponse(responseCode = "400", description = "не нашли по id пользователя")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserGetByIdResponse> getUserById(@PathVariable @Parameter(description = "айдишки") int id) {
        var principal =  (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nameForCheck = principal.getUsername();
        User userViaContext = userService.getUserByUserLogin(nameForCheck);
        User user = userService.getUserById(id);
        if (user.getLoginUser().equals(userViaContext.getLoginUser()) && userViaContext.getUser_type().equals("USER")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    @PostMapping("/deleteUser")
//    public ResponseEntity<HttpStatus> deleteUser() {
//    return ResponseEntity<>(HttpStatus.OK);
//    }

//    @PostMapping
//    public Boolean createUser(@RequestBody @Valid Security security, User user, BindingResult bindingResult ) {
//        if (bindingResult.hasErrors()){
//            for (ObjectError o:bindingResult.getAllErrors()){
//                log.warn("we have some errors" + o);
//            }
//        }
//        return userService.createUser(user,security);
//    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody UserRegistrationRequest userRegistrationRequest) {
      Boolean result = userService.userRegistration(userRegistrationRequest);
        return new ResponseEntity<>(result ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }


    }
