package com.kraluk.greminder.calendar;

import com.google.api.services.calendar.Calendar;
import com.kraluk.greminder.test.base.AbstractNonCalendarRelatedTests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Test suite for class {@link CalendarService}
 *
 * @author lukasz
 */
public class CalendarServiceTests extends AbstractNonCalendarRelatedTests {

    private CalendarService calendarService;

    @Before
    public void setUp() {
        calendar = mock(Calendar.class);

        calendarService = new CalendarService(calendar, "test");
    }

    @Ignore
    @Test
    public void testGetEvents() {
        calendarService.getEvents();
    }
}
