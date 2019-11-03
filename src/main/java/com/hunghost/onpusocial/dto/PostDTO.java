package com.hunghost.onpusocial.dto;

public class PostDTO {
    private String namePost;
    private String content;
    private Long user;
    private Long restriction;
    private Long date;

    @Override
    public String toString() {
        return "PostDTO{" +
                "namePost='" + namePost + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + user.toString() +
                ", restriction=" + restriction +
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

    public Long getRestriction() {
        return restriction;
    }

    public void setRestriction(Long restriction) {
        this.restriction = restriction;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
