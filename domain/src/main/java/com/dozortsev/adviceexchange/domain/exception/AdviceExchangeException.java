package com.dozortsev.adviceexchange.domain.exception;

/**
 * The class {@code AdviceExchangeException} is base exception on Advice Exchange project
 *
 * If this exception occurred it sign to error related with API of this project
 */
public class AdviceExchangeException extends Exception {

    /**
     * Constructs an {@code AdviceExchangeException} with {@code null}
     * as its error detail message.
     */
    public AdviceExchangeException() {}

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param msg the detail message. The detail message is saved for
     *            later retrieval by the {@link #getMessage()} method.
     */
    public AdviceExchangeException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param msg   the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public AdviceExchangeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}