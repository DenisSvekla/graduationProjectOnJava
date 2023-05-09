package com.tms.controller;

import java.util.ArrayList;

import com.tms.config.forValidation.ValidationForOperators;
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
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    ValidationForOperators validationForOperators;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUser() {
        ArrayList<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // TODO добавить получение всех пользователей с помощью dto объекта
    // TODO добавить добавление авто в избранное
    // TODO добавить обновление пользователя

    @Operation(summary = "Отдает пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер"),
            @ApiResponse(responseCode = "400", description = "не нашли по id пользователя")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserGetByIdResponse> getUserById(@PathVariable @Parameter(description = "айдишки") int id) {
        UserGetByIdResponse user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        boolean checkAboutSuccesfullMessage = userService.deleteUser(id);
        return new ResponseEntity <> (checkAboutSuccesfullMessage ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        Boolean result = userService.userRegistration(userRegistrationRequest);
        return new ResponseEntity<>(result ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserUpdateDto userUpdateDto, @PathVariable int id) {
//        User user = userService.updateUser(userUpdateDto,id);
//        if (user !=null) {
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.CONFLICT);
//    }
}
