package com.hunghost.onpusocial.dto;

public class StudygroupDTO {
    private String nameGroup;
    private String descriptionGroup;
    private Long kafedra;
    private Long course;
    private Long stream;
    private  Boolean isNotStudentGroup;

    @Override
    public String toString() {
        return "StudygroupDTO{" +
                "nameGroup='" + nameGroup + '\'' +
                ", descriptionGroup='" + descriptionGroup + '\'' +
                ", kafedra=" + kafedra +
                ", course=" + course +
                ", stream=" + stream +
                ", isNotStudentGroup=" + isNotStudentGroup +
                '}';
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getDescriptionGroup() {
        return descriptionGroup;
    }

    public void setDescriptionGroup(String descriptionGroup) {
        this.descriptionGroup = descriptionGroup;
    }

    public Long getKafedra() {
        return kafedra;
    }

    public void setKafedra(Long kafedra) {
        this.kafedra = kafedra;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public Long getStream() {
        return stream;
    }

    public void setStream(Long stream) {
        this.stream = stream;
    }

    public Boolean getNotStudentGroup() {
        return isNotStudentGroup;
    }

    public void setNotStudentGroup(Boolean notStudentGroup) {
        isNotStudentGroup = notStudentGroup;
    }
}
