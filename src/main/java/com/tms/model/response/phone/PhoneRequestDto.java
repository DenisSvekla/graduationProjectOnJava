package com.tms.model.response.phone;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PhoneRequestDto {

    private String number;
    private String operator;
}
