package com.mai.webApplication.models;

import javax.persistence.*;

@Entity
@Table(name = "statements")
public class Statement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @Column(name = "rating")
    private int rating;

    public int getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getStudent() {
        return student;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
