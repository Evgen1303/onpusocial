package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "kafedra")
public class Kafedra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameKafedra;
    private String descriptionKafedra;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;

    @Override
    public String toString() {
        return "Kafedra{" +
                "id=" + id +
                ", nameKafedra='" + nameKafedra + '\'' +
                ", descriptionKafedra='" + descriptionKafedra + '\'' +
                ", faculty=" + faculty +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameKafedra() {
        return nameKafedra;
    }

    public void setNameKafedra(String nameKafedra) {
        this.nameKafedra = nameKafedra;
    }

    public String getDescriptionKafedra() {
        return descriptionKafedra;
    }

    public void setDescriptionKafedra(String descriptionKafedra) {
        this.descriptionKafedra = descriptionKafedra;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
