package com.tms.model.request.car;

import javax.validation.constraints.Min;

import com.tms.annotation.CheckTransmission;
import lombok.Data;

@Data
public class AddCarRequestDto {

    private String nameBrand;

    private String nameModel;

    @CheckTransmission
    private String transmission;

    private String dateOfIssue;

    private String color;

    private String registrationNumber;

    @Min(value = 1)
    private int price;

    @CheckTransmission
    private String engineType;

    private String city;

}
