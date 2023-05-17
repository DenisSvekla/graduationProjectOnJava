package com.tms.model.request.car;

import javax.validation.constraints.Min;

import com.tms.annotation.CheckTransmission;
import lombok.Data;

@Data
public class AddCarRequestDto {

    private String name_brand;

    private String name_model;

    @CheckTransmission
    private String transmission;

    private String date_of_issue;

    private String color;

    private String registration_number;

    @Min(value = 1)
    private int price;

    @CheckTransmission
    private String engine_type;

    private String city;

}
