package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Raspisanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String timeslot;
    @NotNull
    private int group1;
    @NotNull
    private int group2;

    public Raspisanie() {}

    public Raspisanie(long id, String timeslot, int group1, int group2) {
        this.id = id;
        this.timeslot = timeslot;
        this.group1 = group1;
        this.group2 = group2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public int getGroup1() {
        return group1;
    }

    public void setGroup1(int group1) {
        this.group1 = group1;
    }

    public int getGroup2() {
        return group2;
    }

    public void setGroup2(int group2) {
        this.group2 = group2;
    }
}
