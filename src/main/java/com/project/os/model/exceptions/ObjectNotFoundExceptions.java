package com.project.os.model.exceptions;

public class ObjectNotFoundExceptions extends RuntimeException{

    private static final long serialVersionUID = 5785227560592452706L;

    public ObjectNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundExceptions(String message) {
        super(message);
    }
}
