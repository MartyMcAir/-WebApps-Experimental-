package example.users.model;

import example.users.service.UserService;
import org.springframework.validation.BindingResult;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

/**
 * An interface to represent the form to be used
 *
 * @author Oliver Gierke
 */
public interface UserForm {

    String getUsername();

    String getPassword();

    String getRepeatedPassword();

    /**
     * Validates the {@link UserForm}.
     *
     * @param errors
     * @param userService
     */
    default void validate(BindingResult errors, UserService userService) {
        rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
        rejectIfEmptyOrWhitespace(errors, "repeatedPassword", "user.repeatedPassword.empty");

        if (!getPassword().equals(getRepeatedPassword())) {
            errors.rejectValue("repeatedPassword", "user.password.no-match");
        }

        try {
            userService.findByUsername(new Username(getUsername())).ifPresent(
                    user -> errors.rejectValue("username", "user.username.exists"));
        } catch (IllegalArgumentException o_O) {
            errors.rejectValue("username", "user.username.invalidFormat");
        }
    }
}
