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

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "father_name")
    private String fatherName;

    @OneToMany(mappedBy = "teacher")
    private List<Statement> statements;

    @Column(name = "type_of_control")
    private String typeControl;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public String getTypeControl() {
        return typeControl;
    }

    public void setTypeControl(String typeControl) {
        this.typeControl = typeControl;
    }

    public String getFullName() {
        return getLastName() + " " + getFirstName() + " " + getFatherName();
    }

    public String getShortName() {
        return getLastName() + " " + getFirstName().charAt(0) + "." + getFatherName().charAt(0) + ".";
    }
}
