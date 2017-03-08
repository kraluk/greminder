package com.kraluk.greminder.util;

import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Contains general for whole application methods and constants
 *
 * @author lukasz
 */
@UtilityClass
public final class AppUtils {

    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("Europe/Warsaw");

    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+1");

    public static final String GOOGLE_CALENDAR_DESCRIPTION_PATTERN = "%s|%s|%s";
}
