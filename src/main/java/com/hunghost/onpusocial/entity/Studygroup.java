package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "studygroup")
public class Studygroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameGroup;
    private String descriptionGroup;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "kafedra_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Kafedra kafedra;
    private Long course;
    private Long stream;
    private Boolean isNotStudentGroup = false;

    @OneToMany(mappedBy = "studygroup")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<User> userList;

    public Integer getUserList() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Studygroup() {
    }

    @Override
    public String toString() {
        return "Studygroup{" +
                "id=" + id +
                ", nameGroup='" + nameGroup + '\'' +
                ", descriptionGroup='" + descriptionGroup + '\'' +
                ", kafedra=" + kafedra +
                ", course=" + course +
                ", stream=" + stream +
                ", isNotStudentGroup=" + isNotStudentGroup +
                ", userList=" + userList +
                '}';
    }

    public Boolean getNotStudentGroup() {
        return isNotStudentGroup;
    }

    public void setNotStudentGroup(Boolean notStudentGroup) {
        isNotStudentGroup = notStudentGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Kafedra getKafedra() {
        return kafedra;
    }

    public void setKafedra(Kafedra kafedra) {
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
}
