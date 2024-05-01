package com.shb.hotel.exception;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String message) {
        super(message);
    }

    public ResourseNotFoundException() {
        super("Resource Not Found");
    }
}
