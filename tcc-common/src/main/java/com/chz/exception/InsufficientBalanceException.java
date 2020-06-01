package com.chz.exception;

/**
 * 资源不足异常
 */
public class InsufficientBalanceException extends RuntimeException {
    private static final long serialVersionUID = 6689953065473521009L;

    public InsufficientBalanceException() {

    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
