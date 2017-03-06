package com.kraluk.greminder.calendar.util;

import com.kraluk.greminder.calendar.exception.IllegalDescriptionFormatException;
import com.kraluk.greminder.calendar.model.CalendarEvent;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test suite for class {@link DescriptionParser}
 *
 * @author lukasz
 */
public class DescriptionParserTests {

    @Test
    public void testShouldParseProperly() {
        String referenceDescription =
            String.format(DescriptionParser.DESCRIPTION_PATTERN, "Physics", "Professor Leader",
                "111222333");

        CalendarEvent event = DescriptionParser.parse(referenceDescription);

        assertThat(event).isNotNull();
        assertThat(event.getLeader()).isEqualTo("Professor Leader");
        assertThat(event.getTitle()).isEqualTo("Physics");
        assertThat(event.getPhoneNumber()).isEqualTo("111222333");
    }

    @Test
    public void testShouldParseProperlyWithAdditionalSpaces() {
        String referenceDescription =
            String.format(DescriptionParser.DESCRIPTION_PATTERN, "  Physics II ",
                "  Ass. Prof. Leader  ", " 111 222 333  ");

        CalendarEvent event = DescriptionParser.parse(referenceDescription);

        assertThat(event).isNotNull();
        assertThat(event.getLeader()).isEqualTo("Ass. Prof. Leader");
        assertThat(event.getTitle()).isEqualTo("Physics II");
        assertThat(event.getPhoneNumber()).isEqualTo("111222333");
    }

    @Test
    public void testShouldParseProperlyWithExitingEventInstance() {
        CalendarEvent originalEvent = new CalendarEvent();

        String referenceDescription =
            String.format(DescriptionParser.DESCRIPTION_PATTERN, "Physics", "Professor Leader",
                "111222333");

        CalendarEvent event = DescriptionParser.parse(referenceDescription, originalEvent);

        assertThat(event).isNotNull().isSameAs(originalEvent);
        assertThat(event.getLeader()).isEqualTo("Professor Leader");
        assertThat(event.getTitle()).isEqualTo("Physics");
        assertThat(event.getPhoneNumber()).isEqualTo("111222333");
    }

    @Test
    public void testShouldThrowExceptionWhenDescriptionIsInIllegalFormat() {
        assertThatThrownBy(() -> DescriptionParser.parse("illegaldata")).isInstanceOf(
            IllegalDescriptionFormatException.class);
    }

    @Test
    public void testShouldThrowExceptionWhenDescriptionIsNull() {
        assertThatThrownBy(() -> DescriptionParser.parse(null)).isInstanceOf(
            NullPointerException.class);
    }

    @Test
    public void testShouldThrowExceptionWhenEventIsNull() {
        assertThatThrownBy(() -> DescriptionParser.parse("", null)).isInstanceOf(
            NullPointerException.class);
    }
}
