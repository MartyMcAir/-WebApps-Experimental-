package com.validator;

import com.model.User;
import com.model.forms.SignupForm;
import com.model.forms.UserMultiWrapperForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class UserMultiWrapperValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    // Сначала простые проверки и последними идут, более сложные и те что больше отбирают performance..
    @Override
    public void validate(Object o, Errors errors) {
        UserMultiWrapperForm obj = (UserMultiWrapperForm) o;
        User userForm = obj.getNewUserFromForm();

        rejectIfEmptyOrWhitespace(errors, "username2", "signUpForm.userName.empty");
        rejectIfEmptyOrWhitespace(errors, "email2", "signUpForm.email.empty");
        rejectIfEmptyOrWhitespace(errors, "role2", "userForm.role.empty");

        // Password
        // если хотя бы одно из полей НЕ является пустым _ сначала идёт проверка на то, что пусты ли поля
        if (!obj.isEmptyPasswordFields() || !userForm.getPassword().equals("")) {
            rejectIfEmptyOrWhitespace(errors, "user_password2", "userWrapperForm.current_password.empty");
            rejectIfEmptyOrWhitespace(errors, "password2", "userWrapperForm.confirm_new_password.empty");
            rejectIfEmptyOrWhitespace(errors, "confirm_password2", "userWrapperForm.new_password.empty");

//            if (!(obj.getCurrent_password_text().equals(obj.getPassword()))) { // String сравнение
            // сравнение пароля что ввёл пользователь, c crypt хэшом хранящимся в БД _ и если false then..
//            if (!(encoder.matches(obj.getCurrent_password_text(), obj.getCurrent_password_crypt()))) {
//                errors.rejectValue("current_password_text", "userWrapperForm.current_password.invalid");
//            }

            if (!userForm.getPassword().equals("")) { // _ если поле ввода, для нового пароля не пусто
                // проверка (НОВОГО) пароля на его длину _ если поле не пусто
                if ((userForm.getPassword().length() < 6) | (userForm.getPassword().length() > 20)) {
                    errors.rejectValue("password2", "signUpForm.password.longShort");
                }

                // сравнение предыдущего пароля с тем, что юзер ввёл (проверка юзера)
                if (!obj.getUserOriginBackUp().getPassword().equals(obj.getUser_password2())) {
                    errors.rejectValue("user_password2", "signUpForm.password.invalid");
                }

                // сравнение нового пароля с его подтвержением
                if (!(userForm.getPassword().equals(obj.getConfirm_password2()))) {
                    errors.rejectValue("confirm_password2", "signUpForm.confirmPassword.invalid");
                }
            }
        }

        // Далее идёт код валидации по проще чем проверка пароля, но его проверка идёт ниже т.к. проще для понимания

        // UserName
        // проверка имени на её длину
        if ((userForm.getUsername().length() < 4) || (userForm.getUsername().length() > 20)) {
            errors.rejectValue("username2", "signUpForm.userName.longShort");
        }

//        if (service.findById(obj.getId()) {
//            errors.rejectValue("username2", "signUpForm.userName.exists");
//        }

        // Email
        // проверка почты на её длину
        if (userForm.getEmail().length() > 40) {
            errors.rejectValue("email2", "signUpForm.email.longShort");
        }

        if (!userForm.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email2", "signUpForm.email.invalid");
        }

        //        if (service.findByEmail(obj.getEmail()) {
//            errors.rejectValue("email2", "signUpForm.userName.exists");
//        }
    }

}