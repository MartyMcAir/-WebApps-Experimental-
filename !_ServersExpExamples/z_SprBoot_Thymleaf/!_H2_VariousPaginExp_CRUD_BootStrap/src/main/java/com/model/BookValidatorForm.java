package com.model;

/**
 * An interface to represent the form to be used
 *
 * @author Oliver Gierke
 */
public interface BookValidatorForm {

    String getName();

//    /**
//     * Validates the {@link BookForm}.
//     *
//     * @param errors
//     * @param userService
//     */
//    default void validate(BindingResult errors, BookService userService) {
//        rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty");
//        rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
//        rejectIfEmptyOrWhitespace(errors, "repeatedPassword", "user.repeatedPassword.empty");
//
//        if (!getPassword().equals(getRepeatedPassword())) {
//            errors.rejectValue("repeatedPassword", "user.password.no-match");
//        }
//
//        try {
//            userService.findByUsername(new Book(getUsername())).ifPresent(
//                    user -> errors.rejectValue("username", "user.username.exists"));
//        } catch (IllegalArgumentException o_O) {
//            errors.rejectValue("username", "user.username.invalidFormat");
//        }
//    }
}
