package com.tms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Phone {

    private int id;
    private String number;
    private String operator;
    private int user_id;
}
