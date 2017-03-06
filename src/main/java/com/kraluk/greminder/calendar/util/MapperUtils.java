package com.kraluk.greminder.calendar.util;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Contains useful mapper util methods
 *
 * @author lukasz
 */
@UtilityClass
final class MapperUtils {
    private static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("Europe/Warsaw");

    /**
     * Converts {@link com.google.api.client.util.DateTime} to JDK standard {@link java.time.LocalDateTime} with default time zone specified by {@link #DEFAULT_TIME_ZONE}
     */
    static LocalDateTime convert(com.google.api.client.util.DateTime dateTime) {
        return LocalDateTime
            .ofInstant(Instant.ofEpochMilli(dateTime.getValue()), DEFAULT_TIME_ZONE);
    }
}
