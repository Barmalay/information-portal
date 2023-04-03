package com.mai.webApplication.models;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "group")
    private String group;

    @Column(name = "subject")
    private String subject;

    @Column(name = "surName")
    private String surName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "fatherLand")
    private String fatherLand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
