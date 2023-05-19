package com.tms.model.response.car;

import java.sql.Date;
import java.util.List;

import com.tms.model.domain.Comments;
import lombok.Data;

@Data
public class GetCarByIdResponseDto {

    private String name_brand;
    private String name_model;
    private String transmission;
    private String date_of_issue;
    private String color;
    private String registration_number;
    private int price;
    private String engine_type;
    private String city;
    private Integer count_view;
    private String status;
    private Date created;
    private Date changed;
    private int user_id;
    private List<Comments> comments;
}
