package com.hunghost.onpusocial.dto;

public class PostDTO {
    private String namePost;
    private String content;
    private Long user;
    private Long studygroup;
    private Long kafedra;
    private Long faculty;
    private Boolean subscribers;
    private Long date;

    @Override
    public String toString() {
        return "PostDTO{" +
                "namePost='" + namePost + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", studygroup=" + studygroup +
                ", kafedra=" + kafedra +
                ", faculty=" + faculty +
                ", subscribers=" + subscribers +
                ", date=" + date +
                '}';
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getStudygroup() {
        return studygroup;
    }

    public void setStudygroup(Long studygroup) {
        this.studygroup = studygroup;
    }

    public Long getKafedra() {
        return kafedra;
    }

    public void setKafedra(Long kafedra) {
        this.kafedra = kafedra;
    }

    public Long getFaculty() {
        return faculty;
    }

    public void setFaculty(Long faculty) {
        this.faculty = faculty;
    }

    public Boolean getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Boolean subscribers) {
        this.subscribers = subscribers;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
