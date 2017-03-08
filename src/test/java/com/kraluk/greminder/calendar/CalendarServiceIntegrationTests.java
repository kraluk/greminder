package com.kraluk.greminder.calendar;

import com.kraluk.greminder.calendar.model.CalendarEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static com.kraluk.greminder.util.AppProfile.TEST;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for class {@link CalendarService}
 *
 * @author lukasz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(TEST)
public class CalendarServiceIntegrationTests {

    @Autowired
    private CalendarService calendarService;

    @Test
    public void testShouldGetEvents() {
        List<CalendarEvent> events = calendarService.getEvents();

        assertThat(events).isNotNull();
        assertThat(events.size()).isGreaterThan(0);
    }
}
