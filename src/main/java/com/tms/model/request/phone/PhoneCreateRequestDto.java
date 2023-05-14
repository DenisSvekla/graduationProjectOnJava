package com.tms.model.request.phone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneCreateRequestDto {

    private String number;
    private String operator;
}
