package com.nashss.se.translationtracker.exceptions;

public class DuplicateProgressUpdateException extends RuntimeException {

    private static final long serialVersionUID = -4833824905880453683L;

    /**
     * Exception with no message or cause.
     */
    public DuplicateProgressUpdateException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DuplicateProgressUpdateException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicateProgressUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicateProgressUpdateException(Throwable cause) {
        super(cause);
    }
}
