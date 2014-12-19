package com.dozortsev.adviceexchange.domain.exception;

public class AdviceExchangeException extends Exception {

    public AdviceExchangeException() {}

    public AdviceExchangeException(String msg) {
        super(msg);
    }

    public AdviceExchangeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}