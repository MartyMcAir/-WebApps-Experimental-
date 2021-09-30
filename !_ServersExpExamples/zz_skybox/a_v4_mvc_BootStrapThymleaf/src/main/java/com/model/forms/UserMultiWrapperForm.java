package com.model.forms;

import com.model.User;

public class UserMultiWrapperForm {
    private User userOriginBackUp; // load from DB and Set, to form _ in hidden input for save
    private User newUserFromForm; // for comparison with origin..

    // id one for both objects
    private int id;

    // for Save in hidden fields
    private String username;
    private String password;
    private String email;
    private String role;

    // for Update in Form
    private String username2;
    //      Passwords fields
    private String user_password2; // user know OLD password?
    private String password2;
    private String confirm_password2;

    private String email2;
    private String role2;

    public void setUserOriginBackUp(User userOriginBackUp) {
        this.userOriginBackUp = userOriginBackUp;
        // load data for save like origin in form
        // input type="hidden" ..
        this.id = userOriginBackUp.getId();
        this.username = userOriginBackUp.getUsername();
        this.password = userOriginBackUp.getPassword();

//        if (!userOriginBackUp.getPassword().equals("")) {
//            // на случай если сеттится, из формы в которой нет изменений
//            this.password = userOriginBackUp.getPassword();
//        }

        this.email = userOriginBackUp.getEmail();
        this.role = userOriginBackUp.getRole();

        // load data for show in form
        this.username2 = userOriginBackUp.getUsername();
        this.email2 = userOriginBackUp.getEmail();
        this.role2 = userOriginBackUp.getRole();
    }

    // ........................................................................

    public User getUserOriginBackUp() {
        if (userOriginBackUp == null) {
            User userOrigin = new User();
            userOrigin.setId(id);
            userOrigin.setUsername(username);
            userOrigin.setPassword(password);
            userOrigin.setEmail(email);
            userOrigin.setRole(role);

            this.userOriginBackUp = userOrigin;
        }
        return userOriginBackUp;
    }

    public User getNewUserFromForm() {
        if (newUserFromForm == null) {
            User newUser = new User();
            newUser.setId(id);
            newUser.setUsername(username2);
            newUser.setPassword(password2);
            newUser.setEmail(email2);
            newUser.setRole(role2);

            this.newUserFromForm = newUser;
        }
        return newUserFromForm;
    }

    public User getNewUserFromFormForSave() {
        if (newUserFromForm == null) {
            User newUser = new User();
            newUser.setId(id);
            newUser.setUsername(username2);
            newUser.setEmail(email2);
            newUser.setRole(role2);

            this.newUserFromForm = newUser;
        }
        // если в поле пароль, не было измененно
        if (newUserFromForm.getPassword().equals(""))
            newUserFromForm.setPassword(password);

        return newUserFromForm;
    }

    public void showUserNameFields() {
        System.out.println("this.username: " + this.username);
        System.out.println("this.username2: " + this.username2);
        System.out.println("username: " + username);
        System.out.println("username2: " + username2);
        System.out.println("getUsername(): " + getUsername());
        System.out.println("getUsername2(): " + getUsername2());
    }

    public void showPasswordFields() {
        System.out.println("this.password: " + this.password);
        System.out.println("password: " + password);
        System.out.println("this.getPassword(): " + this.getPassword());

        System.out.println("this.getPassword2(): " + this.getPassword2());
        System.out.println("this.getUser_password2(): " + this.getUser_password2());
        System.out.println("this.getConfirm_password2(): " + this.getConfirm_password2());
    }

    public void showOriginPassword() {
        System.out.println(" _ password: " + password);
        System.out.println(" _ this.password: " + this.password);
        System.out.println(" _ getPassword(): " + getPassword());
    }

    public boolean isEmptyPasswordFields() {
        return password2.equals("") & user_password2.equals("") & confirm_password2.equals("");
    }

    // ........................................................................

    public String getUser_password2() {
        return user_password2;
    }

    public String getConfirm_password2() {
        return confirm_password2;
    }

    // ........................................................................
    // Standard Getters adn Setters
    public void setNewUserFromForm(User newUserFromForm) {
        this.newUserFromForm = newUserFromForm;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public void setUser_password2(String user_password2) {
        this.user_password2 = user_password2;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setConfirm_password2(String confirm_password2) {
        this.confirm_password2 = confirm_password2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getRole2() {
        return role2;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }
}