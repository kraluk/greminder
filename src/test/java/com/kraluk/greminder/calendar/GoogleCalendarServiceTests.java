package com.kraluk.greminder.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.common.exception.GreminderException;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.kraluk.greminder.test.TestDataProvider.generateEvent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for class {@link GoogleCalendarService}
 *
 * @author lukasz
 */
public class GoogleCalendarServiceTests {

    private Events events;
    private Calendar.Events.List calendarEventsList;

    private CalendarService calendarService;

    @Before
    public void setUp() throws IOException {
        Calendar calendar = mock(Calendar.class);
        Calendar.Events calendarEvents = mock(Calendar.Events.class);
        calendarEventsList = mock(Calendar.Events.List.class);
        events = mock(Events.class);

        when(calendar.events()).thenReturn(calendarEvents);
        when(calendarEvents.list(anyString())).thenReturn(calendarEventsList);
        when(calendarEventsList.setTimeMin(any(DateTime.class))).thenReturn(calendarEventsList);
        when(calendarEventsList.setTimeMax(any(DateTime.class))).thenReturn(calendarEventsList);

        calendarService = new GoogleCalendarService(calendar, "test");
    }

    @Test
    public void testGetEvents() throws IOException {
        when(calendarEventsList.execute()).thenReturn(events);
        when(events.getItems()).thenReturn(Arrays.asList(generateEvent(1), generateEvent(2)));

        List<CalendarEvent> calendarEvents = calendarService.getEvents();

        assertThat(calendarEvents).isNotNull().hasSize(2);
    }

    @Test
    public void testGetEventsAndThrowException() throws IOException {
        when(calendarEventsList.execute()).thenThrow(new IllegalStateException());

        assertThatThrownBy(() -> calendarService.getEvents())
            .isInstanceOf(GreminderException.class);
    }
}
