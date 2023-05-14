package com.tms.model.domain;

import java.sql.Date;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Subscription {
    private int id;
    private Date expireDate;
}
