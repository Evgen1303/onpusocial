package com.hunghost.onpusocial.dto;

public class UserDTO {
    private String firstName;
    private String lastName;
    private Long birthday;
    private String email;
    private String phone;
    private String description;
    private String photo;
    private Long studygroup;
    private Boolean starosta;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
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
                '}';
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

    public Long getStudygroup() {
        return studygroup;
    }

    public void setStudygroup(Long studygroup) {
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
}
