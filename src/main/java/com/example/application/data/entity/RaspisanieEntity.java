package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity
public class RaspisanieEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    private String timeslot;

    private String group1;

    private String group2;

    public RaspisanieEntity() {}

//    public RaspisanieEntity(long id, String timeslot, String group1, String group2) {
//        this.id = id;
//        this.timeslot = timeslot;
//        this.group1 = group1;
//        this.group2 = group2;
//    }

    public RaspisanieEntity(String timeslot, String group1, String group2) {

        this.timeslot = timeslot;
        this.group1 = group1;
        this.group2 = group2;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getGroup2() {
        return group2;
    }

    public void setGroup2(String group2) {
        this.group2 = group2;
    }
}
