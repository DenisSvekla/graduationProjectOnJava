package com.tms.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tms.annotation.CheckOperatorName;
import lombok.Data;

@Data
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_new_new_id_seq")
    @SequenceGenerator(name = "phone_new_new_id_seq", sequenceName = "phone_new_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "number")
    private String number;

    @CheckOperatorName
    @Column(name = "operator")
    private String operator;

    @Column(name = "user_id")
    private int userId;
}
