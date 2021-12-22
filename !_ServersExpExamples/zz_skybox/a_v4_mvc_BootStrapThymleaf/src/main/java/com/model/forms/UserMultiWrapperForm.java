package com.model.forms;

import com.model.UserHere;

public class UserMultiWrapperForm {
    private UserHere userHereOriginBackUp; // load from DB and Set, to form _ in hidden input for save
    private UserHere newUserHereFromForm; // for comparison with origin..

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

    public void setUserOriginBackUp(UserHere userHereOriginBackUp) {
        this.userHereOriginBackUp = userHereOriginBackUp;
        // load data for save like origin in form
        // input type="hidden" ..
        this.id = userHereOriginBackUp.getId();
        this.username = userHereOriginBackUp.getUsername();
        this.password = userHereOriginBackUp.getPassword();

//        if (!userOriginBackUp.getPassword().equals("")) {
//            // на случай если сеттится, из формы в которой нет изменений
//            this.password = userOriginBackUp.getPassword();
//        }

        this.email = userHereOriginBackUp.getEmail();
        this.role = userHereOriginBackUp.getRole();

        // load data for show in form
        this.username2 = userHereOriginBackUp.getUsername();
        this.email2 = userHereOriginBackUp.getEmail();
        this.role2 = userHereOriginBackUp.getRole();
    }

    // ........................................................................

    public UserHere getUserOriginBackUp() {
        if (userHereOriginBackUp == null) {
            UserHere userHereOrigin = new UserHere();
            userHereOrigin.setId(id);
            userHereOrigin.setUsername(username);
            userHereOrigin.setPassword(password);
            userHereOrigin.setEmail(email);
            userHereOrigin.setRole(role);

            this.userHereOriginBackUp = userHereOrigin;
        }
        return userHereOriginBackUp;
    }

    public UserHere getNewUserFromForm() {
        if (newUserHereFromForm == null) {
            UserHere newUserHere = new UserHere();
            newUserHere.setId(id);
            newUserHere.setUsername(username2);
            newUserHere.setPassword(password2);
            newUserHere.setEmail(email2);
            newUserHere.setRole(role2);

            this.newUserHereFromForm = newUserHere;
        }
        return newUserHereFromForm;
    }

    public UserHere getNewUserFromFormForSave() {
        if (newUserHereFromForm == null) {
            UserHere newUserHere = new UserHere();
            newUserHere.setId(id);
            newUserHere.setUsername(username2);
            newUserHere.setEmail(email2);
            newUserHere.setRole(role2);

            this.newUserHereFromForm = newUserHere;
        }
        // если в поле пароль, не было измененно
        if (newUserHereFromForm.getPassword().equals(""))
            newUserHereFromForm.setPassword(password);

        return newUserHereFromForm;
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
    public void setNewUserFromForm(UserHere newUserHereFromForm) {
        this.newUserHereFromForm = newUserHereFromForm;
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