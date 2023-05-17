package com.tms.utils.validation.forAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tms.annotation.CheckStatusForDeletingCarAnnotation;
import org.springframework.beans.factory.annotation.Value;

public class CheckStatusForDeletingCar implements ConstraintValidator<CheckStatusForDeletingCarAnnotation, String> {

    @Value("${service.auto.status}")
    private Set<String> status;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> check = status.stream().filter(value::equals).collect(Collectors.toList());
        return check.size() == 1;
    }
}
