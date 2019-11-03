package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePost;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private Long restriction;
    private Long date;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", namePost='" + namePost + '\'' +
                ", content='" + content + '\'' +
                ", userId =" + user.toString() +
                ", restriction=" + restriction +
                ", date=" + date +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
