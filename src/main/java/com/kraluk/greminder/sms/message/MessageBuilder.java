package com.kraluk.greminder.sms.message;

import com.google.common.base.Strings;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.sms.exception.SmsConfigurationException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Text message content builder
 *
 * @author lukasz
 */
@Component
@Slf4j
public class MessageBuilder {
    private static final String MESSAGE_PATTERN = "^.*%s.*%s.*%s.*%s.*$";

    private final String messageTemplate;

    @Autowired
    public MessageBuilder(@Value("${sms.message.template}") String messageTemplate) {
        validateTemplatePattern(messageTemplate);

        this.messageTemplate = messageTemplate;
    }

    public String build(CalendarEvent event) {
        return String.format(messageTemplate,
            event.getTitle(),
            event.getLeader(),
            event.getStartDate().toLocalDate(),
            event.getStartDate().toLocalTime());
    }

    private static void validateTemplatePattern(String messageTemplate) {
        if (Strings.isNullOrEmpty(messageTemplate)) {
            throw new SmsConfigurationException("Message template cannot be null or empty!");
        }

        if (!messageTemplate.matches(MESSAGE_PATTERN)) {
            throw new SmsConfigurationException("Message template don't match required pattern!");
        }
    }
}
