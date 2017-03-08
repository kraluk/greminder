package com.kraluk.greminder.sms.exception;

import com.kraluk.greminder.common.exception.GreminderException;

/**
 * Covers all exceptional situations related with configuration text messages service
 *
 * @author lukasz
 */
public class SmsConfigurationException extends GreminderException {

    public SmsConfigurationException(String s) {
        super(s);
    }

    public SmsConfigurationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
