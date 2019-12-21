package com.hunghost.onpusocial.dto;

public class AuthorizationDTO {
    private String login;
    private String pass;

    @Override
    public String toString() {
        return "AuthorizationDTO{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
