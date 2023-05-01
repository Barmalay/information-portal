package com.mai.webApplication.models;

public class StatementForm {
    private String subject;
    private String typeControl;
    private String formEducation;
    private String group;
    private String teacherName;

    public StatementForm() {
        // Конструктор без параметров
    }

    public StatementForm(String subject, String typeControl,
                         String formEducation, String group,
                         String teacherName) {
        this.subject = subject;
        this.typeControl = typeControl;
        this.formEducation = formEducation;
        this.group = group;
        this.teacherName = teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTypeControl() {
        return typeControl;
    }

    public void setTypeControl(String typeControl) {
        this.typeControl = typeControl;
    }

    public String getFormEducation() {
        return formEducation;
    }

    public void setFormEducation(String formEducation) {
        this.formEducation = formEducation;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

