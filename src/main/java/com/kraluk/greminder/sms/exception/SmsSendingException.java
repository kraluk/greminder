package com.kraluk.greminder.sms.exception;

import com.kraluk.greminder.common.exception.GreminderException;

/**
 * Covers all exceptional situations related with sending text messages
 *
 * @author lukasz
 */
public class SmsSendingException extends GreminderException {

    public SmsSendingException(String s) {
        super(s);
    }

    public SmsSendingException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
