package com.tms.model.response.Car;

import java.sql.Date;

import lombok.Data;

@Data
public class GetAllCarsResponseDto {
    private String name_brand;
    private String name_model;
    private String transmission;
    private String date_of_issue;
    private String color;
    private int price;
    private String engine_type;
    private String city;
    private Date created;
    private Date changed;
}
