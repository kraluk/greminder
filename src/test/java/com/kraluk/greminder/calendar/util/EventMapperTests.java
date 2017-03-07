package com.kraluk.greminder.calendar.util;

import com.google.api.services.calendar.model.Event;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.test.TestDataProvider;
import com.kraluk.greminder.util.AppUtils;

import org.junit.Ignore;
import org.junit.Test;

import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for class {@link EventMapper}
 *
 * @author lukasz
 */
public class EventMapperTests {

    // TODO: comparing strings is not the smartest idea here...
    @Ignore
    @Test
    public void testShouldMap() {

        Event googleEvent = TestDataProvider.generateEvent(1);
        CalendarEvent event = EventMapper.map(googleEvent);

        assertThat(event).isNotNull();
        assertThat(event.getLeader()).isEqualTo("Ass. Prof. Leader");
        assertThat(event.getTitle()).isEqualTo("Physics II");
        assertThat(event.getPhoneNumber()).isEqualTo("111222333");

        // bleh, Google's DateTime always consider timezone
        assertThat(ZonedDateTime.of(event.getStartDate(), AppUtils.DEFAULT_TIME_ZONE)
            .format(ISO_OFFSET_DATE_TIME))
            .isEqualTo(googleEvent.getStart().getDateTime().toStringRfc3339());
        assertThat(ZonedDateTime.of(event.getEndDate(), AppUtils.DEFAULT_TIME_ZONE)
            .format(ISO_OFFSET_DATE_TIME))
            .isEqualTo(googleEvent.getEnd().getDateTime().toStringRfc3339());
    }
}
