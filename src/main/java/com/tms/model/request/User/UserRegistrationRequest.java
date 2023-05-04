package com.tms.model.request.User;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRegistrationRequest {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Date birthday_date;
    private String user_type;
    private Date created;
    private Date changed;
    private String loginUser;
    private String passwordUser;
}
