package com.tms.model.response.user;

import java.sql.Date;

import lombok.Data;


@Data
public class UserGetByIdResponse {
    private String name;
    private String surname;
    private String email;
    private Date birthday_date;
    private String user_type;
    private String loginUser;
}
