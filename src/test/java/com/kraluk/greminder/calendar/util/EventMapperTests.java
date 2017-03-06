package com.kraluk.greminder.calendar.util;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.kraluk.greminder.calendar.model.CalendarEvent;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for class {@link EventMapper}
 *
 * @author lukasz
 */
public class EventMapperTests {

    @Test
    public void testShouldMap() {

        CalendarEvent event = EventMapper.map(generateEvent());

        assertThat(event).isNotNull();
        assertThat(event.getLeader()).isEqualTo("Ass. Prof. Leader");
        assertThat(event.getTitle()).isEqualTo("Physics II");
        assertThat(event.getPhoneNumber()).isEqualTo("111222333");
        assertThat(event.getStartDate().toString()).isEqualTo("2011-11-11T01:00");
        assertThat(event.getEndDate().toString()).isEqualTo("2011-11-13T01:00");
    }

    private static Event generateEvent() {
        DateTime startDate = new DateTime("2011-11-11");
        EventDateTime eventStartDate = new EventDateTime();
        eventStartDate.setDateTime(startDate);

        DateTime endDate = new DateTime("2011-11-13");
        EventDateTime eventEndDate = new EventDateTime();
        eventEndDate.setDateTime(endDate);

        Event googleEvent = new Event();
        googleEvent.setStart(eventStartDate);
        googleEvent.setEnd(eventEndDate);
        googleEvent
            .setDescription(String.format(DescriptionParser.DESCRIPTION_PATTERN, "  Physics II ",
                "  Ass. Prof. Leader  ", " 111 222 333  "));

        return googleEvent;
    }
}
