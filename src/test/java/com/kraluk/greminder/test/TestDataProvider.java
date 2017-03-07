package com.kraluk.greminder.test;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.kraluk.greminder.calendar.model.CalendarEvent;

import java.time.LocalDateTime;

import static com.kraluk.greminder.util.AppUtils.GOOGLE_CALENDAR_DESCRIPTION_PATTERN;

/**
 * Simple dummy data generator for test purposes
 *
 * @author lukasz
 */
public final class TestDataProvider {

    private TestDataProvider() {
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated!");
    }

    public static Event generateEvent(int seed) {
        LocalDateTime currentDate = LocalDateTime.now();

        DateTime startDate = new DateTime(currentDate.toString());
        EventDateTime eventStartDate = new EventDateTime();
        eventStartDate.setDateTime(startDate);

        DateTime endDate = new DateTime(currentDate.plusHours(seed).toString());
        EventDateTime eventEndDate = new EventDateTime();
        eventEndDate.setDateTime(endDate);

        Event googleEvent = new Event();
        googleEvent.setStart(eventStartDate);
        googleEvent.setEnd(eventEndDate);
        googleEvent
            .setDescription(String.format(GOOGLE_CALENDAR_DESCRIPTION_PATTERN, "  Physics II ",
                "  Ass. Prof. Leader  ", " 111 222 333  "));

        return googleEvent;
    }

    public static CalendarEvent generateCalendarEvent(int seed) {
        return CalendarEvent.builder()
            .phoneNumber("111222333")
            .leader("prof. Leader")
            .title(String.format("Example Classes %s", seed))
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now().plusHours(seed))
            .build();
    }
}
