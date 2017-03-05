package com.kraluk.greminder.calendar;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
}