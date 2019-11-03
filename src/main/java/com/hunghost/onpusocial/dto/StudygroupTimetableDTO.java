package com.hunghost.onpusocial.dto;

public class StudygroupTimetableDTO {
    private Long idGrouop;
    private Long idTimetable;
    private Long day;
    private Long week;
    private Long nomer;

    @Override
    public String toString() {
        return "StudygroupTimetableDTO{" +
                "idGrouop=" + idGrouop +
                ", idTimetable=" + idTimetable +
                ", day=" + day +
                ", week=" + week +
                ", nomer=" + nomer +
                '}';
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
