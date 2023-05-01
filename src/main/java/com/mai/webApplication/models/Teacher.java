package com.mai.webApplication.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "group_student")
    private String groupStudent;

    @Column(name = "subject")
    private String subject;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "father_land")
    private String fatherLand;

    @OneToMany(mappedBy = "teacher")
    private List<Statement> statements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGroupStudent() {
        return groupStudent;
    }

    public void setGroupStudent(String groupStudent) {
        this.groupStudent = groupStudent;
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

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public String getFullName() {
        return getSurName() + " " + getFirstName() + " " + getFatherLand();
    }

    public String getShortName() {
        return getSurName() + " " + getFirstName().charAt(0) + "." + getFatherLand().charAt(0) + ".";
    }
}
