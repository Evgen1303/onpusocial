package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="namePost")
    private String namePost;
    @Column(name="content")
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "studygroup_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Studygroup studygroup;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "kafedra_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Kafedra kafedra;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "faculty_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;
    private Boolean subscribers;
    private Long date;


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", namePost='" + namePost + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", studygroup=" + studygroup +
                ", kafedra=" + kafedra +
                ", faculty=" + faculty +
                ", subscribers=" + subscribers +
                ", date=" + date +
                '}';
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

    public String getUser() {
        return user.getUsername();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Studygroup getStudygroup() {
        return studygroup;
    }

    public void setStudygroup(Studygroup studygroup) {
        this.studygroup = studygroup;
    }

    public Kafedra getKafedra() {
        return kafedra;
    }

    public void setKafedra(Kafedra kafedra) {
        this.kafedra = kafedra;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
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
