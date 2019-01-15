package com.gold.GoldPoc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Prabhakar Verma
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "dob")
    private String dob;

    @Column(name = "create_time", columnDefinition = "DATE DEFAULT NULL")
    private Date createTime ;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}