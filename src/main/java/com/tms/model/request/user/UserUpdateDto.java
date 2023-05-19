package com.tms.model.request.user;


import java.sql.Date;

import lombok.Data;

@Data
public class UserUpdateDto {

    private String name;

    private String surname;

    private String email;

    private Date birthdayDate;

    private String loginUser;


}
