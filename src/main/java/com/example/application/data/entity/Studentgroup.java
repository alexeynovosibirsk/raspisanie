package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Studentgroup implements Cloneable{

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String number;

    public Studentgroup() {};

    public Studentgroup(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public Studentgroup clone() {
        try {
            return (Studentgroup) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
