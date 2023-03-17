package com.kafein.cms.exceptions;

public class AlreadyExistException extends RuntimeException {
    private static final long serialVerisionUID = 1;

    public AlreadyExistException(String message) {
        super(message + " already exist.");
    }
}
