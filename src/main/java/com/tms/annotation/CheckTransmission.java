package com.tms.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tms.utils.validation.forAnnotation.CheckTransmissionLogic;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = CheckTransmissionLogic.class)
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckTransmission {

    String message() default "you have an invalid transmission";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
