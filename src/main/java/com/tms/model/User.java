package com.tms.model;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private Date birthday_date;
    private Date created;
    private Date changed;
    private Security security_id;
    private boolean is_deleted;
    private String user_type;
}
