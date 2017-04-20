package com.kraluk.greminder.scheduler;

import com.kraluk.greminder.calendar.CalendarService;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.sms.message.MessageBuilder;
import com.kraluk.greminder.sms.sender.SmsSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Core Scheduler Service for whole application
 *
 * @author lukasz
 */
@Service
@ConditionalOnProperty(prefix = "scheduler", name = "enabled", matchIfMissing = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SchedulerService {
    private static final int DEFAULT_FIXED_DELAY = 50000;

    private final CalendarService calendarService;
    private final SmsSender smsSender;
    private final MessageBuilder messageBuilder;

    @Scheduled(fixedDelay = DEFAULT_FIXED_DELAY)
    public void remind() {
        List<CalendarEvent> events = calendarService.getEvents();

        log.info("Attempting to send messages regarding to '{}' events...", events.size());

        for (CalendarEvent event : events) {
            log.debug("Attempting to process event '{}'...", event);

            String message = messageBuilder.build(event);
            smsSender.send(event.getPhoneNumber(), message);

            log.debug("Event processed successfully.");
        }

        log.info("Sending messages completed successfully.");
    }
}
