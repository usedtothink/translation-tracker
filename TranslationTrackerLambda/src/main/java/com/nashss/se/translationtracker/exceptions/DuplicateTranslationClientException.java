package com.nashss.se.translationtracker.exceptions;

/**
 * Exception to throw when a given translation client name already exists in the database.
 */
public class DuplicateTranslationClientException extends RuntimeException {

    private static final long serialVersionUID = -2336795214862597818L;

    /**
     * Exception with no message or cause.
     */
    public DuplicateTranslationClientException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DuplicateTranslationClientException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicateTranslationClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicateTranslationClientException(Throwable cause) {
        super(cause);
    }
}
