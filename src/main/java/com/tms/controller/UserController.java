package com.tms.controller;

import java.util.ArrayList;

import com.tms.model.domain.User;
import com.tms.model.request.user.UserRegistrationRequest;
import com.tms.model.request.user.UserUpdateDto;
import com.tms.model.response.user.UserGetByIdResponse;
import com.tms.service.UserService;
import com.tms.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(summary = "return all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return users"),
            @ApiResponse(responseCode = "409", description = "not found users")
    })
    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUser() {
        ArrayList<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @Operation(summary = "return user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return user"),
            @ApiResponse(responseCode = "400", description = "not found user")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserGetByIdResponse> getUserById(@PathVariable int id) {
        UserGetByIdResponse user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "change status for users is_deleted to true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "user deleted"),
            @ApiResponse(responseCode = "404", description = "not found user")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "registration user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created"),
            @ApiResponse(responseCode = "409", description = "have problems with data")
    })
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user = userService.userRegistration(userRegistrationRequest);
        return new ResponseEntity<>(user.getLoginUser() != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @Operation(summary = "registration user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user updated"),
            @ApiResponse(responseCode = "409", description = "have problems with data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserUpdateDto userUpdateDto, @PathVariable int id) {
        User user = userService.updateUser(userUpdateDto, id);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
