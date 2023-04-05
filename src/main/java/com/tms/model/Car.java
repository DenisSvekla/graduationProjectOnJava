package com.tms.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name ="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_new_id_seq")
    @SequenceGenerator(name="car_new_id_seq", sequenceName = "car_id_seq",allocationSize = 1)
    private int id;

    @Column(name = "name_brand")
    private String name_brand;

    @Column(name = "name_model")
    private String name_model;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "date_of_issue")
    private Date date_of_issue;

    @Column(name = "color")
    private String color;

    @Column(name = "registration_number")
    private String registration_number;

    @Column(name = "price")
    private int price;

    @Column(name = "engine_type")
    private String engine_type;

    @Column(name = "city")
    private String city;

    @Column(name = "count_view")
    private Integer count_view;

    @Column(name = "status")
    private String status;

}
