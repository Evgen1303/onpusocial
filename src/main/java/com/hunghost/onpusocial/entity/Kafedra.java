package com.hunghost.onpusocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "kafedra")
public class Kafedra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameKafedra;
    private String descriptionKafedra;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Faculty faculty;

    @OneToMany(mappedBy = "kafedra", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Studygroup> studygroupSet;

    public Integer getStudygroupSet() {
        return studygroupSet.size();
    }

    public void setStudygroupSet(Set<Studygroup> studygroupSet) {
        this.studygroupSet = studygroupSet;
    }

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
