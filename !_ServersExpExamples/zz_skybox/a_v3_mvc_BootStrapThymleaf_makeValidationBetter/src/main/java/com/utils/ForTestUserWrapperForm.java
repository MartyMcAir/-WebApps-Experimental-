package com.utils;

import com.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ForTestUserWrapperForm {
    private User user;
    private String current_password_text;
    private String new_password_text;
    private String confirm_new_password;

    private String current_password_crypt;
    private String new_password_crypt;

    // ................................
    private int id = 1;
    private String username = "test_username_0";
    private String email = "test_email_0@mail.com";
    private String role = "USER";
    private String password = "test_password_0";

    public ForTestUserWrapperForm() {
    }

    public ForTestUserWrapperForm(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.current_password_crypt = new BCryptPasswordEncoder().encode(user.getPassword());
    }

    public void refreshNewPasswordVariable() {
        this.current_password_text = "";
        this.new_password_text = "";
        this.confirm_new_password = "";
    }

    public User getNewUser() {
        User user_new = new User();
        user_new.setId(getId());
        user_new.setUsername(getUsername());
        user_new.setEmail(getEmail());
        user_new.setRole(getRole());
        user_new.setPassword(getNew_password_text());
        this.user = user_new;
        return user_new;
    }

    // Standard Getters and Setters ..............................................................................

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrent_password_text() {
        return current_password_text;
    }

    public void setCurrent_password_text(String current_password_text) {
        this.current_password_text = current_password_text;
    }

    public String getNew_password_text() {
        return new_password_text;
    }

    public void setNew_password_text(String new_password_text) {
        this.new_password_text = new_password_text;
    }

    public String getConfirm_new_password() {
        return confirm_new_password;
    }

    public void setConfirm_new_password(String confirm_new_password) {
        this.confirm_new_password = confirm_new_password;
    }

    public String getCurrent_password_crypt() {
        return current_password_crypt;
    }

    public void setCurrent_password_crypt(String current_password_crypt) {
        this.current_password_crypt = current_password_crypt;
    }

    public String getNew_password_crypt() {
        return new_password_crypt;
    }

    public void setNew_password_crypt(String new_password_crypt) {
        this.new_password_crypt = new_password_crypt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}