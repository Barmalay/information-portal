package com.mai.webApplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "group")
    private String group;

    @Column(name = "surName")
    private String surName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "fatherLand")
    private String fatherLand;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherLand() {
        return fatherLand;
    }

    public void setFatherLand(String fatherLand) {
        this.fatherLand = fatherLand;
    }
}
