package com.tms.model.request.user;

import javax.validation.constraints.NotNull;
import java.sql.Date;

import lombok.Data;

@Data
public class UserRegistrationRequest {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private Date birthdayDate;

    @NotNull
    private String loginUser;

    @NotNull
    private String passwordUser;
}
