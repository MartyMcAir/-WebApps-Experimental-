package com.model.forms;

import com.model.User;
import org.springframework.validation.BindingResult;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class SignupForm {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserFromForm() {
        return new User(username, password, email, "USER");
    }
}