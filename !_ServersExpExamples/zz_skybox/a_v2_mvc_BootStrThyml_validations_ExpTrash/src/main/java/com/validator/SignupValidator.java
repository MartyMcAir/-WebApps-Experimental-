package com.validator;

import com.model.forms.SignupForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class SignupValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignupForm obj = (SignupForm) o;

//        if (obj.getUsername().isEmpty() & obj.getConfirmPassword().isEmpty()
//                & obj.getEmail().isEmpty() & obj.getPassword().isEmpty()) {
//            errors.rejectValue("username", "book.allFields.require1");
//        }

        rejectIfEmptyOrWhitespace(errors, "username", "signUpForm.userName.empty");
        rejectIfEmptyOrWhitespace(errors, "email", "signUpForm.email.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "signUpForm.password.empty");
        rejectIfEmptyOrWhitespace(errors, "confirmPassword", "signUpForm.confirmPassword.empty");

        // ............................................................................................

        // UserName
//        if (service.findById(obj.getId()) {
//            errors.rejectValue("username", "signUpForm.userName.duplicate");
//        }

        if (!(obj.getUsername().length() > 4) & !(obj.getUsername().length() < 20)) {
            errors.rejectValue("username", "signUpForm.userName.longShort");
        }

        // Email
        if (emailValidator.isValid(obj.getEmail())) {
            errors.rejectValue("email", "signUpForm.email.invalid");
        }

        if (obj.getEmail().length() > 30) {
            errors.rejectValue("email", "signUpForm.email.longShort");
        }
        //        if (service.findByEmail(obj.getEmail()) {
//            errors.rejectValue("email", "signUpForm.userName.duplicate");
//        }

        // Password
        if (!obj.getPassword().equals(obj.getConfirmPassword())) {
            errors.rejectValue("password", "signUpForm.password.invalid");
        }

        if (!(obj.getPassword().length() > 6) & !(obj.getPassword().length() < 20)) {
            errors.rejectValue("password", "signUpForm.password.longShort");
        }

    }

//    public void validate(Object target, Errors errors) {
//        SignupForm signupForm = (SignupForm) target;
//
////        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username must not be empty.");
//
//        String username = signupForm.getUsername();
//        if ((username.length()) < 3 | username.length() > 10) {
////            errors.rejectValue("username", "username.tooLong", "Username must not more than 16 characters.");
//            errors.rejectValue("username", "{username.must.between}", "two");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "username.must.between");
////        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");
//        if (!(signupForm.getPassword()).equals(signupForm
//                .getConfirmPassword())) {
//            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
//        }
//
//        if (!EmailValidator.getInstance().isValid(signupForm.getEmail())) {
//            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
//        }
//    }
}