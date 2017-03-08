package com.kraluk.greminder.sms.message;

import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.sms.exception.SmsConfigurationException;
import com.kraluk.greminder.test.TestDataProvider;
import com.kraluk.greminder.test.TestUtils;

import org.junit.Before;
import org.junit.Test;

import static com.kraluk.greminder.test.TestUtils.MESSAGE_TEMPLATE_KEY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

/**
 * Test suite for class {@link MessageBuilder}
 *
 * @author lukasz
 */
public class MessageBuilderTests {

    private String messageTemplate;

    @Before
    public void setUp() {
        messageTemplate = TestUtils.getTestProperty(MESSAGE_TEMPLATE_KEY);
    }

    @Test
    public void testShouldThrowExceptionDueToNullifiedTemplate() {
        assertThatThrownBy(() -> new MessageBuilder(null))
            .isInstanceOf(SmsConfigurationException.class);
    }

    @Test
    public void testShouldThrowExceptionDueToEmptyTemplate() {
        assertThatThrownBy(() -> new MessageBuilder(""))
            .isInstanceOf(SmsConfigurationException.class);
    }

    @Test
    public void testShouldThrowExceptionDueToIllegalTemplate() {
        assertThatThrownBy(() -> new MessageBuilder("illegal_%s_template"))
            .isInstanceOf(SmsConfigurationException.class);
    }


    @Test
    public void testShouldBuildMessage() {
        CalendarEvent calendarEvent = TestDataProvider.generateCalendarEvent(1);

        String message = new MessageBuilder(messageTemplate).build(calendarEvent);

        assertThat(message).contains(calendarEvent.getLeader());
        assertThat(message).contains(calendarEvent.getTitle());
        assertThat(message).contains(calendarEvent.getStartDate().toLocalDate().toString());
        assertThat(message).contains(calendarEvent.getStartDate().toLocalTime().toString());
    }
}
