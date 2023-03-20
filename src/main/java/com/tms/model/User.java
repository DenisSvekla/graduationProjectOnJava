package com.tms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private Date birthday_date;
    private int security_id;
    private String user_type;
    private boolean is_deleted;
    private Date created;
    private Date changed;


}
