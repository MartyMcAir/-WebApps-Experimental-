package com.handlersAndExceptions;

public class CustomerDoesNotExistException extends RuntimeException {
    public CustomerDoesNotExistException(Long customerId) {
        super(String.format("A customer with ID '%d' doesn't exist!", customerId));
    }

}
