package com.tms.model.request.User;


import java.sql.Date;

import lombok.Data;

@Data
public class UserUpdateDto {

    private String name;

    private String surname;

    private String email;

    private Date birthday_date;

    private String loginUser;


}
