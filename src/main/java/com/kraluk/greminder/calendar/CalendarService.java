package com.kraluk.greminder.calendar;

import com.kraluk.greminder.calendar.model.CalendarEvent;

import java.util.List;

/**
 * Provides all necessary methods to work with web-based Calendars
 *
 * @author lukasz
 */
public interface CalendarService {

    List<CalendarEvent> getEvents();
}
