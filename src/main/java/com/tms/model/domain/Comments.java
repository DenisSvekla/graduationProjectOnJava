package com.tms.model.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import lombok.Data;

@Data
@Entity
@Table(name = "lt_comments_car")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_new_id_seq")
    @SequenceGenerator(name = "comments_new_id_seq", sequenceName = "comments_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "car_id")
    private int car_id;

    @NotNull
    @Column(name = "comments")
    private String comments;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "created")
    private Date created;
    @Column(name = "changed")
    private Date changed;

}
