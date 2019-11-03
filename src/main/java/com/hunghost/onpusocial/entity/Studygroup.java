package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "studygroup")
public class Studygroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameGroup;
    private String descriptionGroup;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "kafedra_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Kafedra kafedra;
    private Long course;
    private Long stream;

    @Override
    public String toString() {
        return "Studygroup{" +
                "id=" + id +
                ", nameGroup='" + nameGroup + '\'' +
                ", descriptionGroup='" + descriptionGroup + '\'' +
                ", kafedra=" + kafedra +
                ", course=" + course +
                ", stream=" + stream +
                '}';
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
