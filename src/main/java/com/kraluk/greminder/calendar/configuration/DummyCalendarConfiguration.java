package com.kraluk.greminder.calendar.configuration;

import com.google.api.services.calendar.Calendar;
import com.kraluk.greminder.util.AppProfile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

/**
 * Dummy Calendar factory for development purposes only!
 *
 * @author lukasz
 */
@Configuration
@Profile(AppProfile.DEVELOPMENT)
public class DummyCalendarConfiguration {

    @Bean
    @Lazy
    public Calendar calendar() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
