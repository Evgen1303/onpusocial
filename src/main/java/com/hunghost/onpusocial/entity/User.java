package com.hunghost.onpusocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hunghost.onpusocial.annotation.Phone;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * Simple JavaBean domain object that represents a User
 *
 * @author Eugene Karnuta
 * @version 1.1
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Long birthday;

    @Column(unique=true)
    @NotNull
    @Email(message = "Not suitable format for Email")
    private String email;
    @Phone
    private String phone;
    private String description;
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "studygroup_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Studygroup studygroup;
    private Boolean starosta;

    @Column(unique=true)
    @NotNull
    private String username;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> authorities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = { @JoinColumn(name = "channel_id") },
            inverseJoinColumns = { @JoinColumn(name = "subscriber_id") }
    )
    private Set<User> subscribers;


    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = { @JoinColumn(name = "subscriber_id") },
            inverseJoinColumns = { @JoinColumn(name = "channel_id") }
    )
    private Set<User> subscriptions = new HashSet<>();

    public User(String firstName, String lastName, Long birthday, @NotNull @Email(message = "Not suitable format for Email") String email, String phone, String description, String photo, Studygroup studygroup, Boolean starosta, @NotNull String username, String password, Collection<Role> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.photo = photo;
        this.studygroup = studygroup;
        this.starosta = starosta;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String firstName, String lastName, Long birthday, @NotNull @Email(message = "Not suitable format for Email") String email, String phone, String description, String photo, Studygroup studygroup, Boolean starosta, @NotNull String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.photo = photo;
        this.studygroup = studygroup;
        this.starosta = starosta;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", studygroup=" + studygroup +
                ", starosta=" + starosta +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Studygroup getStudygroup() {
        return studygroup;
    }

    public void setStudygroup(Studygroup studygroup) {
        this.studygroup = studygroup;
    }

    public Boolean getStarosta() {
        return starosta;
    }

    public void setStarosta(Boolean starosta) {
        this.starosta = starosta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Role> authorities) {

        this.authorities = authorities;
    }
    public void addAuthorities(Role authoriti) {

        this.authorities.add(authoriti);
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscriptions(User user){
        subscriptions.add(user);
    }

    public void addSubscribers(User user){
        subscribers.add(user);
    }

    public Boolean deleteSubscriptions(User user){
        return  subscriptions.remove(user);
    }

    @JsonIgnore
    public Set<User> getSubscribers() {
        return subscribers;
    }

    @JsonIgnore
    public Set<User> getSubscriptions() {
        return subscriptions;
    }
}
