package com.kraluk.greminder.sms.sender;

import com.google.common.base.Strings;
import com.kraluk.greminder.sms.exception.SmsSendingException;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.run;

/**
 * Interface for any kind of SMS Sender used in the application
 *
 * @author lukasz
 */
public interface SmsSender {

    int SMS_SIZE_LIMIT = 160;

    /**
     * Sends a text message to the given number with the given content
     *
     * @param to      a phone number
     * @param content a message content
     * @return a sending status
     */
    String send(String to, String content);

    default void checkMessageSize(String text) {
        Match(text).of(

            Case($(Strings::isNullOrEmpty), e -> run(() -> {
                throw new SmsSendingException("It's illegal to send an empty message!");
            })),

            Case($(e -> e.length() > SMS_SIZE_LIMIT), e -> run(() -> {
                throw new SmsSendingException(
                    String.format("Exceeded character limit (%s) per message!", SMS_SIZE_LIMIT));
            })),

            Case($(), () -> Void.TYPE) // bleh, the simplest solution to do nothing?
        );
    }
}