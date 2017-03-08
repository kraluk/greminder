package com.kraluk.greminder.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.calendar.util.EventMapper;
import com.kraluk.greminder.common.exception.GreminderException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javaslang.control.Try;

import static com.kraluk.greminder.util.AppProfile.PRODUCTION;

/**
 * Provides Google Calendar features and manipulation on it
 *
 * @author lukasz
 */
@Service
@Profile(PRODUCTION)
@Slf4j
class GoogleCalendarService implements CalendarService {
    private final static int DEFAULT_HOUR_INTERVAL = 24;

    private final Calendar calendar;
    private final String calendarName;

    @Autowired
    public GoogleCalendarService(Calendar calendar,
                                 @Value("${calendar.name}") String calendarName) {
        this.calendar = calendar;
        this.calendarName = calendarName;
    }

    @Override
    public List<CalendarEvent> getEvents() {
        Date currentDate = new Date();

        DateTime minTime = new DateTime(currentDate); // time zone?
        DateTime maxTime = new DateTime(
            currentDate.toInstant().plus(DEFAULT_HOUR_INTERVAL, ChronoUnit.HOURS).toEpochMilli());

        log.info(
            "Attempting to obtain events for calendar '{}' for time period between '{}' and '{}'",
            calendarName, minTime, maxTime);

        Events events = Try.of(() -> calendar.events()
            .list(calendarName)
            .setTimeMin(minTime)
            .setTimeMax(maxTime)
            .execute()
        ).getOrElseThrow(e -> new GreminderException("Unable to obtain Calendar's events!", e));

        List<Event> items = events.getItems();

        log.info("Successfully obtained '{}' events.", items.size());

        return items
            .stream()
            .map(EventMapper::map)
            .collect(Collectors.toList());
    }
}
