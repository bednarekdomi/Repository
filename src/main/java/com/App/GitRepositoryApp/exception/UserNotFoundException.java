package com.App.GitRepositoryApp.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND_EXCEPTION_ERROR = "Repository not found by given user name";

    public UserNotFoundException() {
        super(USER_NOT_FOUND_EXCEPTION_ERROR);
    }
}