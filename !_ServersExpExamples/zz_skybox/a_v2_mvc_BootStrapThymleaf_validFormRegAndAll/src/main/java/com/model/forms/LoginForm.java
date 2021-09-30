package com.model.forms;

public class LoginForm {

    private String login;
    private String password;

    public LoginForm() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}