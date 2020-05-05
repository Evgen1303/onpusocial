package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String facultyName;
    private String facultyDescription;

//    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Set<Kafedra> kafedraList;
//
//    public Integer getKafedraList() {
//        if(!kafedraList.isEmpty()){
//            return kafedraList.size();
//        }else{
//            return null;
//        }
//
//    }
//
//    public void setKafedraList(Set<Kafedra> kafedraList) {
//        this.kafedraList = kafedraList;
//    }

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
