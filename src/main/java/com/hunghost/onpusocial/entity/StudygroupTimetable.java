package com.hunghost.onpusocial.entity;

import javax.persistence.*;

@Entity
@Table(name = "grouptimetable")
public class StudygroupTimetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idGrouop;
    private Long idTimetable;
    private Long day;
    private Long week;
    private Long nomer;

    @Override
    public String toString() {
        return "StudygroupTimetableDTO{" +
                "id=" + id +
                ", idGrouop=" + idGrouop +
                ", idTimetable=" + idTimetable +
                ", day=" + day +
                ", week=" + week +
                ", nomer=" + nomer +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGrouop() {
        return idGrouop;
    }

    public void setIdGrouop(Long idGrouop) {
        this.idGrouop = idGrouop;
    }

    public Long getIdTimetable() {
        return idTimetable;
    }

    public void setIdTimetable(Long idTimetable) {
        this.idTimetable = idTimetable;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getWeek() {
        return week;
    }

    public void setWeek(Long week) {
        this.week = week;
    }

    public Long getNomer() {
        return nomer;
    }

    public void setNomer(Long nomer) {
        this.nomer = nomer;
    }
}
