package com.hunghost.onpusocial.dto;

public class FacultyDTO {

    private String facultyName;
    private String facultyDescription;

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "facultyName='" + facultyName + '\'' +
                ", facultyDescription='" + facultyDescription + '\'' +
                '}';
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
