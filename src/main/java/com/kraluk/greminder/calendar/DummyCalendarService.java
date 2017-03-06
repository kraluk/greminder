package com.kraluk.greminder.calendar;

import com.google.api.services.calendar.model.Event;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

import static com.kraluk.greminder.util.AppProfile.DEVELOPMENT;
import static com.kraluk.greminder.util.AppProfile.TEST;

/**
 * Dummy Calendar service implementation for the development and test purposes.
 *
 * @author lukasz
 */
@Service
@Profile({DEVELOPMENT, TEST})
@Slf4j
class DummyCalendarService implements CalendarService {

    @PostConstruct
    public void init() {
        log.warn("DEV ONLY: dummy instance created.");
    }

    @Override
    public List<Event> getEvents() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
