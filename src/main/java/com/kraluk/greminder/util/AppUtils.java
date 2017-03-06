package com.kraluk.greminder.util;

import lombok.experimental.UtilityClass;

import java.time.ZoneId;

/**
 * Contains general for whole application methods and constants
 *
 * @author lukasz
 */
@UtilityClass
public final class AppUtils {

    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("Europe/Warsaw");
}
