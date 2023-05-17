package com.tms.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tms.utils.validation.forAnnotation.CheckOperatorNameLogic;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = CheckOperatorNameLogic.class)
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckOperatorName {
    String message() default "you have an invalid operator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
