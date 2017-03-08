package com.kraluk.greminder.sms.util;

import lombok.experimental.UtilityClass;

import pl.smsapi.api.response.SendStatusResponse;

import java.util.stream.Collectors;

/**
 * SMS Response related utilities class
 *
 * @author lukasz
 */
@UtilityClass
public final class SmsResponseUtils {
    private static final String JOIN_DELIMITER = "; ";
    private static final String PRETTY_PATTERN = "Number: '%s', Status: '%s'";

    public static String getPrettyResponse(SendStatusResponse response) {
        return response.getList()
            .stream()
            .map(e -> String.format(PRETTY_PATTERN, e.getNumber(), e.getStatus()))
            .collect(Collectors.joining(JOIN_DELIMITER));
    }
}