package com.kraluk.greminder.common.exception;

/**
 * General application exception
 *
 * @author lukasz
 */
public class GreminderException extends RuntimeException {

    public GreminderException(String s) {
        super(s);
    }

    public GreminderException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
