package com.myorganisation.technova.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student doesn't exist");
    }

    public StudentNotFoundException(String m) {
        super(m);
    }
}
