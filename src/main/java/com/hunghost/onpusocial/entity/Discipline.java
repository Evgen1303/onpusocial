package com.hunghost.onpusocial.entity;

import javax.persistence.*;

@Entity
@Table(name = "timetable")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String discipline;
    private String teacher;

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", discipline='" + discipline + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
