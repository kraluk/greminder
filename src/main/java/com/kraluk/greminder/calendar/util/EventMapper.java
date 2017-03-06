package com.kraluk.greminder.calendar.util;

import com.google.api.services.calendar.model.Event;
import com.kraluk.greminder.calendar.model.CalendarEvent;

import lombok.experimental.UtilityClass;

import static com.kraluk.greminder.calendar.util.MapperUtils.convert;

/**
 * Maps Google's {@link com.google.api.services.calendar.model.Event} to application-based {@link com.kraluk.greminder.calendar.model.CalendarEvent}
 *
 * @author lukasz
 */
@UtilityClass
public final class EventMapper {

    public static CalendarEvent map(final Event googleEvent) {
        CalendarEvent calendarEvent = new CalendarEvent();

        calendarEvent.setStartDate(convert(googleEvent.getStart().getDateTime()));
        calendarEvent.setEndDate(convert(googleEvent.getEnd().getDateTime()));

        calendarEvent = DescriptionParser.parse(googleEvent.getDescription(), calendarEvent);

        return calendarEvent;
    }
}
