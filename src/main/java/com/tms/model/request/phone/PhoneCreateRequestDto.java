package com.tms.model.request.phone;

import com.tms.annotation.CheckOperatorName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneCreateRequestDto {

    private String number;

    @CheckOperatorName
    private String operator;
}
