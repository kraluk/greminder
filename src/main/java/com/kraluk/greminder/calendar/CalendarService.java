package com.kraluk.greminder.calendar;

import com.google.api.services.calendar.model.Event;

import java.util.List;

/**
 * Provides all necessary methods to work with web-based Calendars
 *
 * @author lukasz
 */
public interface CalendarService {

    List<Event> getEvents();
}
