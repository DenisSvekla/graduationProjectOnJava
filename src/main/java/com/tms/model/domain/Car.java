package com.tms.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.sql.Date;

import com.tms.annotation.CheckTransmission;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_new_id_seq")
    @SequenceGenerator(name = "car_new_id_seq", sequenceName = "car_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "name_brand")
    private String nameBrand;

    @Column(name = "name_model")
    private String nameModel;

    @CheckTransmission
    @Column(name = "transmission")
    private String transmission;

    @Column(name = "date_of_issue")
    private String dateOfIssue;

    @Column(name = "color")
    private String color;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Min(value = 1)
    @Column(name = "price")
    private int price;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "city")
    private String city;

    @Column(name = "count_view")
    private Integer countView;

    @Column(name = "stasus")
    private String status;

    @Column(name = "created")
    private Date created;

    @Column(name = "changed")
    private Date changed;

    @Column(name = "user_id")
    private int userId;

}
