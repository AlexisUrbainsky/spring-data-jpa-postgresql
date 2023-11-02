package com.alexurba.springdatajpapostgresql.model;

import jakarta.persistence.*;

@Entity
@Table(name= "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String emailDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailDesc() {
        return emailDesc;
    }

    public void setEmailDesc(String email) {
        this.emailDesc = email;
    }
}
