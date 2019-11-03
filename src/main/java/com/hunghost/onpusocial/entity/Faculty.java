package com.hunghost.onpusocial.entity;

import javax.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String facultyName;
    private String facultyDescription;

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                ", facultyDescription='" + facultyDescription + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyDescription() {
        return facultyDescription;
    }

    public void setFacultyDescription(String facultyDescription) {
        this.facultyDescription = facultyDescription;
    }
}
