package com.kraluk.greminder.sms.sender;

import com.google.common.base.Strings;
import com.kraluk.greminder.sms.exception.SmsSendingException;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.API.run;

/**
 * Interface for any kind of SMS Sender used in the application
 *
 * @author lukasz
 */
public interface SmsSender {

    int SMS_SIZE_LIMIT = 160;

    String send(String to, String content);

    default void checkMessageSize(String text) {
        Match(text).of(

            Case(Strings::isNullOrEmpty, e -> run(() -> {
                throw new SmsSendingException("It's illegal to send an empty message!");
            })),

            Case(e -> e.length() > SMS_SIZE_LIMIT, e -> run(() -> {
                throw new SmsSendingException("Exceeded character limit (160) per message!");
            })),

            Case($(), () -> null) // bleh, the simplest solution to do nothing?
        );
    }
}