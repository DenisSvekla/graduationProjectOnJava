package com.tms.controller;

import com.tms.model.User;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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

    @Operation(summary = "Отдает пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер"),
            @ApiResponse(responseCode = "400", description = "не нашли по id пользователя")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Parameter(description = "айдишки") int id) {
        User user = userService.getUserById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    @PostMapping
//    public Boolean createUser(@RequestBody @Valid Security security, User user, BindingResult bindingResult ) {
//        if (bindingResult.hasErrors()){
//            for (ObjectError o:bindingResult.getAllErrors()){
//                log.warn("we have some errors" + o);
//            }
//        }
//        return userService.createUser(user,security);
//    }



    }
