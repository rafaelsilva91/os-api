package com.project.os.model.exceptions;

public class DataIntegratyViolationException extends RuntimeException{

    private static final long serialVersionUID = 5785227560592452706L;

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataIntegratyViolationException(String message) {
        super(message);
    }

}
