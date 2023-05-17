package com.tms.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;

import lombok.Data;


@Data
@Entity
@Table(name = "subscription_table")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionT_table_new_id_seq")
    @SequenceGenerator(name = "subscriptionT_table_new_id_seq", sequenceName = "subscriptionT_table_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "user_id")
    private int userId;
}
