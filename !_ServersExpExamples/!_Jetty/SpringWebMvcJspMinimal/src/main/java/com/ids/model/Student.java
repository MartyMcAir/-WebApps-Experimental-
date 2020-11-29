/**
 *
 */
package com.ids.model;

import java.util.Date;

/**
 * @author danhnc
 *
 */
public class Student {

    private String name;

    private String email;

    private Date birthdate;

    public Student(String name, String email, Date birth) {
        this.name = name;
        this.email = email;
        this.birthdate = birth;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
