package com.tms.model.request.car;

import com.tms.annotation.CheckTransmission;
import lombok.Data;


@Data
public class UpdateCarRequestDto {

    private String name_brand;
    private String name_model;
    @CheckTransmission
    private String transmission;
    private String date_of_issue;
    private String color;
    private String registration_number;
    private int price;
    private String engine_type;
    private String city;
}
