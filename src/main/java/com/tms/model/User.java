package com.tms.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Entity
@Table(name ="user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name="user_id_seq_gen", sequenceName = "user_new_id_seq",allocationSize = 1)
    private Integer id;

    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name ="birthday_date")
    private Date birthday_date;

    @Column(name = "security_id")
    private int security_id;

    @Column(name = "user_type")
    private String user_type;

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @Column(name = "created")
    private Date created;

    @Column(name = "changed")
    private Date changed;

}
