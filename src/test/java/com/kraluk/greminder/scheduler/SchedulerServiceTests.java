package com.kraluk.greminder.scheduler;

import com.kraluk.greminder.calendar.CalendarService;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.sms.message.MessageBuilder;
import com.kraluk.greminder.sms.sender.SmsSender;
import com.kraluk.greminder.test.TestDataProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test suite for class {@link SchedulerService}
 *
 * @author lukasz
 */
public class SchedulerServiceTests {

    @Mock
    private CalendarService calendarService;

    @Mock
    private SmsSender smsSender;

    @Mock
    private MessageBuilder messageBuilder;

    private SchedulerService schedulerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(calendarService.getEvents())
            .thenReturn(IntStream.range(0, 5).mapToObj(
                TestDataProvider::generateCalendarEvent).collect(Collectors.toList()));

        when(messageBuilder.build(any(CalendarEvent.class)))
            .thenReturn("complete_message");

        when(smsSender.send(anyString(), anyString()))
            .thenReturn("OK");

        schedulerService = new SchedulerService(calendarService, smsSender, messageBuilder);
    }

    @Test
    public void testRemind() {

        try {
            schedulerService.remind();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
