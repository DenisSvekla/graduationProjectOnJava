package com.tms.model.request.car;

import com.tms.annotation.CheckTransmission;
import lombok.Data;


@Data
public class UpdateCarRequestDto {

    private String nameBrand;

    private String nameModel;

    @CheckTransmission
    private String transmission;

    private String dateOfIssue;

    private String color;

    private String registrationNumber;

    private int price;

    private String engineType;

    private String city;
}
