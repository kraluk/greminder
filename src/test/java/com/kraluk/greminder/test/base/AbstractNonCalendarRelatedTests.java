package com.kraluk.greminder.test.base;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;

import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Base for all tests which don't require Google's {@link Calendar} component
 *
 * @author lukasz
 */
@SuppressWarnings("unused")
public class AbstractNonCalendarRelatedTests {

    @MockBean
    protected Calendar calendar;

    @MockBean
    protected Credential credential;
}
