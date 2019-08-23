package com.gold.GoldPoc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Prabhakar Verma
 */

@Data
@Entity
@Table( name = "user" )
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", unique = true, nullable = false )
    private Long id;

    @Column( name = "name", length = 100 )
    private String name;

    @Column( name = "password", length = 50 )
    private String password;

    @Column( name = "email", length = 100 )
    private String email;

    @Column( name = "dob" )
    private String dob;

    @Column( name = "create_time", columnDefinition = "DATE DEFAULT NULL" )
    private Date createTime;

}