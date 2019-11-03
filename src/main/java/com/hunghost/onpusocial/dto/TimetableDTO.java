package com.hunghost.onpusocial.dto;

public class TimetableDTO {
    private String discipline;
    private String teacher;

    @Override
    public String toString() {
        return "TimetableDTO{" +
                "discipline='" + discipline + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
