package com.kraluk.greminder.calendar.util;

import com.google.api.services.calendar.model.Event;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.test.TestDataProvider;

import org.assertj.core.data.Percentage;
import org.junit.Ignore;
import org.junit.Test;

import static com.kraluk.greminder.util.AppUtils.DEFAULT_ZONE_OFFSET;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for class {@link EventMapper}
 *
 * @author lukasz
 */
public class EventMapperTests {
    private static final long MILLISECONDS_MULTIPLIER = 1000L;
    private static final Percentage PERCENTAGE_OFFSET = Percentage.withPercentage(0.0000001);

    @Ignore // TODO: fix this test!
    @Test
    public void testShouldMap() {

        Event googleEvent = TestDataProvider.generateEvent(1);
        CalendarEvent event = EventMapper.map(googleEvent);

        assertThat(event).isNotNull();
        assertThat(event.getLeader()).isEqualTo("Ass. Prof. Leader");
        assertThat(event.getTitle()).isEqualTo("Physics II");
        assertThat(event.getPhoneNumber()).isEqualTo("111222333");

        // bleh, Google's DateTime always consider time zones
        assertThat(
            event.getStartDate().toEpochSecond(DEFAULT_ZONE_OFFSET) * MILLISECONDS_MULTIPLIER)
            .isCloseTo(googleEvent.getStart().getDateTime().getValue(), PERCENTAGE_OFFSET);

        assertThat(
            event.getEndDate().toEpochSecond(DEFAULT_ZONE_OFFSET) * MILLISECONDS_MULTIPLIER)
            .isCloseTo(googleEvent.getEnd().getDateTime().getValue(), PERCENTAGE_OFFSET);
    }
}
