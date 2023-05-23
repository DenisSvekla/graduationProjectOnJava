package com.tms.model.request.user;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserGetById {

    private String name;

    private String surname;

    private String email;

    private Date birthdayDate;
}
