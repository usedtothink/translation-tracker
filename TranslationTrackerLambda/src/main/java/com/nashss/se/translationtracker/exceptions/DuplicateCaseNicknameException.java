package com.nashss.se.translationtracker.exceptions;

/**
 * Exception to throw when a given translation case nickname already exists in the database.
 */
public class DuplicateCaseNicknameException extends RuntimeException {

    private static final long serialVersionUID = -2336795214862597818L;

    /**
     * Exception with no message or cause.
     */
    public DuplicateCaseNicknameException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DuplicateCaseNicknameException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicateCaseNicknameException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DuplicateCaseNicknameException(Throwable cause) {
        super(cause);
    }
}
