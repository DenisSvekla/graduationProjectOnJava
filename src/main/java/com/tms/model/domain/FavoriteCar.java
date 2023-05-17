package com.tms.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lt_favorites_car_user_car")
public class FavoriteCar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lt_favorites_car_user_car_new_id_seq")
    @SequenceGenerator(name = "lt_favorites_car_user_car_new_id_seq", sequenceName = "lt_favorites_car_user_car_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "car_id")
    private int carId;


}
