package com.utils;

import com.model.UserHere;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTest {

//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////        System.out.println(encoder.encode("123456"));
//        // $2a$10$jWCQd0nzQ0DCGUG8zeRIeuQ19imYosHFYdP9IsCgL5OzPShR2gvjK
//        // $2a$10$r48JngqSXsrwlwV4w.xQpOlYQD81QhEZ0.I5olsI.mIcwA2G2p9PG
//        // $2a$10$lC/m1Fuh587..fwcUqNVQOmWw8dzbeneRiPPoiEf25o/1k3/MJC1q
//
//        System.out.println(encoder.encode("$2a$10$lC/m1Fuh587..fwcUqNVQOmWw8dzbeneRiPPoiEf25o/1k3/MJC1q"));
//    }

    private final String plainPassword = "admin";
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // form forum and
    // http://websystique.com/spring-security/spring-security-4-password-encoder-bcrypt-example-with-hibernate/
    // https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
    // https://mkyong.com/spring-security/spring-security-password-hashing-example/
    @Test
    public void bCryptPasswordEncoder_test_6() {
        String encodedPassword = encoder.encode(plainPassword);
        assertTrue(encoder.matches(plainPassword, encodedPassword));
        System.out.println(encodedPassword);

        String encodedPassword_2 = encoder.encode(plainPassword);
        assertTrue(encoder.matches(plainPassword, encodedPassword));
        System.out.println(encodedPassword_2);

        String encodedPassword_3 = encoder.encode(plainPassword);
        assertTrue(encoder.matches(plainPassword, encodedPassword_2));
        System.out.println(encodedPassword_3);

        String encodedPassword_4 = encoder.encode(plainPassword);
        assertTrue(encoder.matches(plainPassword, encodedPassword_3));
        System.out.println(encodedPassword_4);
    }

    @Test
    public void myTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        UserHere usr1 = new UserHere("user888", "123456", "email@mail.com", "ADMIN");
        ForTestUserWrapperForm form = new ForTestUserWrapperForm(usr1);
        form.setCurrent_password_crypt(new BCryptPasswordEncoder().encode(usr1.getPassword()));


        System.out.println("form.getPassword(): " + form.getPassword());
        System.out.println("form.getCurrent_password_crypt(): " + form.getCurrent_password_crypt());

        System.out.println(encoder.matches(form.getPassword(), form.getCurrent_password_crypt()));
        assertTrue(encoder.matches(form.getPassword(), form.getCurrent_password_crypt()));
    }
}