package com.nashss.se.translationtracker.exceptions;

public class DuplicatePaymentRecordException extends RuntimeException {

    private static final long serialVersionUID = -2662595776468113781L;

    /**
     * Exception with no message or cause.
     */
    public DuplicatePaymentRecordException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DuplicatePaymentRecordException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicatePaymentRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicatePaymentRecordException(Throwable cause) {
        super(cause);
    }
}
