package com.kraluk.greminder.sms.message;

import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.test.TestDataProvider;
import com.kraluk.greminder.test.TestUtils;

import org.junit.Before;
import org.junit.Test;

import static com.kraluk.greminder.test.TestUtils.MESSAGE_TEMPLATE_KEY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test suite for class {@link MessageBuilder}
 *
 * @author lukasz
 */
public class MessageBuilderTests {

    private MessageBuilder messageBuilder;

    @Before
    public void setUp() {
        String template = TestUtils.getTestProperty(MESSAGE_TEMPLATE_KEY);

        messageBuilder = new MessageBuilder(template);
    }

    @Test
    public void testShouldBuildMessage() {
        CalendarEvent calendarEvent = TestDataProvider.generateCalendarEvent(1);

        String message = messageBuilder.build(calendarEvent);

        assertThat(message).contains(calendarEvent.getLeader());
        assertThat(message).contains(calendarEvent.getTitle());
        assertThat(message).contains(calendarEvent.getStartDate().toLocalDate().toString());
        assertThat(message).contains(calendarEvent.getStartDate().toLocalTime().toString());
    }
}
