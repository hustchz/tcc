package com.chz.exception;

public class CancellingException extends RuntimeException {


    public CancellingException(Throwable cause) {
        super(cause);
    }
}
