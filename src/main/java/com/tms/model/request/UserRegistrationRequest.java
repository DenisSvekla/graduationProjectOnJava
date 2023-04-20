package com.tms.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

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
