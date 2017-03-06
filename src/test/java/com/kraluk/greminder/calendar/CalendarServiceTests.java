package com.kraluk.greminder.calendar;

import com.google.api.services.calendar.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Test suite for class {@link GoogleCalendarService}
 *
 * @author lukasz
 */
// TODO: refactor!
public class CalendarServiceTests {

    private GoogleCalendarService calendarService;

    @Before
    public void setUp() {
        Calendar calendar = mock(Calendar.class);

        calendarService = new GoogleCalendarService(calendar, "test");
    }

    @Ignore
    @Test
    public void testGetEvents() {

        try {
            calendarService.getEvents();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
