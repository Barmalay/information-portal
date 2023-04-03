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

    @Column(name = "group_student")
    private String groupStudent;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "father_land")
    private String fatherLand;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGroup() {
        return groupStudent;
    }

    public void setGroupStudent(String groupStudent) {
        this.groupStudent = groupStudent;
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
