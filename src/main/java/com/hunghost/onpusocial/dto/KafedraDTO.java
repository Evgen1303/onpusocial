package com.hunghost.onpusocial.dto;

public class KafedraDTO {

    private String nameKafedra;
    private String descriptionKafedra;
    private Long faculty;

    @Override
    public String toString() {
        return "KafedraDTO{" +
                "nameKafedra='" + nameKafedra + '\'' +
                ", descriptionKafedra='" + descriptionKafedra + '\'' +
                ", faculty=" + faculty +
                '}';
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

    public Long getFaculty() {
        return faculty;
    }

    public void setFaculty(Long faculty) {
        this.faculty = faculty;
    }
}
