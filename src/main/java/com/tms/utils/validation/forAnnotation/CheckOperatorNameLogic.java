package com.tms.utils.validation.forAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tms.annotation.CheckOperatorName;
import org.springframework.beans.factory.annotation.Value;

public class CheckOperatorNameLogic implements ConstraintValidator<CheckOperatorName, String> {

    @Value("${service.phone.operator}")
    private Set<String> listOperator;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> check = listOperator.stream().filter(value::equals).collect(Collectors.toList());
        return check.size() == 1;
    }
}
