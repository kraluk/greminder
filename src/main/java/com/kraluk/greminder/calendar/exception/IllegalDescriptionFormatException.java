package com.kraluk.greminder.calendar.exception;

import com.kraluk.greminder.common.exception.GreminderException;

/**
 * Should be thrown when given description is in not proper format
 *
 * @author lukasz
 */
public class IllegalDescriptionFormatException extends GreminderException {

    public IllegalDescriptionFormatException(String s) {
        super(s);
    }
}
