package com.kraluk.greminder.sms.message;

import com.kraluk.greminder.calendar.model.CalendarEvent;

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

    private final String messageTemplate;

    @Autowired
    public MessageBuilder(@Value("${sms.message.template}") String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String build(CalendarEvent event) {
        return String.format(messageTemplate,
            event.getTitle(),
            event.getLeader(),
            event.getStartDate().toLocalDate(),
            event.getStartDate().toLocalTime());
    }
}
