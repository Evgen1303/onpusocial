package com.hunghost.onpusocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studygroup_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Studygroup studygroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kafedra_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Kafedra kafedra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Faculty faculty;

    private Boolean subscribers;
    private Boolean alluser;
    private Long date;
    private Long userIdfield;

    @OneToMany(mappedBy = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<PostComment> postCommentSet;

    @OneToMany(mappedBy = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private  Set<ServerFile> serverFileSet;


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
                ", alluser=" + alluser +
                ", date=" + date +
                ", userIdfield=" + userIdfield +
                ", postCommentSet=" + postCommentSet +
                ", serverFileSet=" + serverFileSet +
                '}';
    }

    public Integer getPostCommentSet() {
        return postCommentSet.size();
    }

    public void setPostCommentSet(Set<PostComment> postCommentSet) {
        this.postCommentSet = postCommentSet;
    }

    public Set<ServerFile> getServerFileSet() {
        return serverFileSet;
    }

    public void setServerFileSet(Set<ServerFile> serverFileSet) {
        this.serverFileSet = serverFileSet;
    }

    public Long getUserIdfield() {
        return user.getId();
    }

    public void setUserIdfield(Long userIdfield) {
        this.userIdfield = userIdfield;
    }

    public Boolean getAlluser() {
        return alluser;
    }

    public void setAlluser(Boolean alluser) {
        this.alluser = alluser;
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
        return this.user.getUsername();
    }

    public ServerFile getOwnerPhoto(){
        return this.user.getProfilephoto();
    }

    @JsonIgnore
    public User getUserObject(){
        return this.user;
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
