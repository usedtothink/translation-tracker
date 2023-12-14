package com.nashss.se.translationtracker.exceptions;

public class PaymentRecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2336795214862597818L;

    /**
     * Exception with no message or cause.
     */
    public PaymentRecordNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public PaymentRecordNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public PaymentRecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public PaymentRecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
