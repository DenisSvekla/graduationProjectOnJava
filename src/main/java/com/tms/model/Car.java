package com.tms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
public class Car {
    private int id;
    private String name_brand;
    private String name_model;
    private String transmission;
    private Date date_of_issue;
    private String color;
    private String registration_number;
    private int price;
    private String engine_type;
    private String city;
    private Integer count_view;
    private String status;

}
