package com.tms.config.forValidation;

import java.util.Set;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "validators.user")
public class ValidationForOperators {

    private Set<String> phone;
}
