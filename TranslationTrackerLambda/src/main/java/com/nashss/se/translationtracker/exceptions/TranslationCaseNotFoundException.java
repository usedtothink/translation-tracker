package com.nashss.se.translationtracker.exceptions;

/**
 * Exception to throw when a given translation case ID is not found in the database.
 */
public class TranslationCaseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2336795214862597818L;

    /**
     * Exception with no message or cause.
     */
    public TranslationCaseNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public TranslationCaseNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public TranslationCaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public TranslationCaseNotFoundException(Throwable cause) {
        super(cause);
    }
}
