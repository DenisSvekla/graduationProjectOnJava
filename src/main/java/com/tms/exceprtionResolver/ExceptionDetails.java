package com.tms.exceprtionResolver;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
}
