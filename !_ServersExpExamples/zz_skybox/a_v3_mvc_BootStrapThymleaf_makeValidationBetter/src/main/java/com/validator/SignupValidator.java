package com.validator;

import com.model.forms.SignupForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

// Сначала простые проверки и последними идут, более сложные, и далее
// те что больше отбирают performance.., подключения к бд и прочее последние проверки..
@Component
public class SignupValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

//    @Autowired
//    private UserService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignupForm obj = (SignupForm) o;

        // Check Empty..
        rejectIfEmptyOrWhitespace(errors, "username", "signUpForm.userName.empty");
        rejectIfEmptyOrWhitespace(errors, "email", "signUpForm.email.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "signUpForm.password.empty");
        rejectIfEmptyOrWhitespace(errors, "confirmPassword", "signUpForm.confirmPassword.empty");

        // ............................................................................................

        // Check Length..
        // UserName
        if ((obj.getUsername().length() < 4) || (obj.getUsername().length() > 20)) {
            errors.rejectValue("username", "signUpForm.userName.longShort");
        }

        // Email
        if (obj.getEmail().length() > 40) {
            errors.rejectValue("email", "signUpForm.email.longShort");
        }

        // Password
        if ((obj.getPassword().length() < 6) || (obj.getPassword().length() > 20)) {
            errors.rejectValue("password", "signUpForm.password.longShort");
        }

        // ............................................................................................

        // Password
        if (!(obj.getPassword().equals(obj.getConfirmPassword()))) {
            errors.rejectValue("password", "signUpForm.password.invalid");
        }

        // Email
        if (!obj.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "signUpForm.email.invalid");
        }

        // ............................................................................................

        // Check in Data Base
        // UserName
//        Optional<User> useByName = service.getUseByName(obj.getUsername());
//        if (useByName.isPresent()) {
//            errors.rejectValue("username", "signUpForm.userName.duplicate");
//        }

        // Email
//        if (service.getUserByEmail(obj.getEmail()).isPresent()) {
//            errors.rejectValue("email", "signUpForm.email.duplicate");
//        }

    }

}