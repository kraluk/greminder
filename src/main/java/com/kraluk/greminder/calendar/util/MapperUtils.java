package com.kraluk.greminder.calendar.util;

import com.kraluk.greminder.util.AppUtils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Contains useful mapper util methods
 *
 * @author lukasz
 */
@UtilityClass
final class MapperUtils {

    /**
     * Converts {@link com.google.api.client.util.DateTime} to JDK standard {@link java.time.LocalDateTime} with default time zone specified by {@link AppUtils#DEFAULT_TIME_ZONE}
     */
    static LocalDateTime convert(final com.google.api.client.util.DateTime dateTime) {
        return LocalDateTime
            .ofInstant(Instant.ofEpochMilli(dateTime.getValue()), AppUtils.DEFAULT_TIME_ZONE);
    }
}
