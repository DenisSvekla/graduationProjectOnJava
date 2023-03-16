package com.tms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Security {
    private int id;
    private String login;
    private String password;
}
