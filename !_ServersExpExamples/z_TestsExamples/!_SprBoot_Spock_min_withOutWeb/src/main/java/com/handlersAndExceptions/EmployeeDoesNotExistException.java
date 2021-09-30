package com.handlersAndExceptions;

public class EmployeeDoesNotExistException extends RuntimeException {
    public EmployeeDoesNotExistException(Long customerId) {
        super(String.format("A customer with ID '%d' doesn't exist!", customerId));
    }

}
