package com.tms.utils.validation.forAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tms.annotation.CheckTransmission;
import org.springframework.beans.factory.annotation.Value;

public class CheckTransmissionLogic implements ConstraintValidator<CheckTransmission, String> {

    @Value("${service.auto.transmissions}")
    private Set<String> listTransmissions;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> check = listTransmissions.stream().filter(value::contains).collect(Collectors.toList());
        return check.size() == 1;
    }
}
