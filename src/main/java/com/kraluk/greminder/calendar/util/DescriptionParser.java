package com.kraluk.greminder.calendar.util;

import com.kraluk.greminder.calendar.exception.IllegalDescriptionFormatException;
import com.kraluk.greminder.calendar.model.CalendarEvent;
import com.kraluk.greminder.calendar.model.DescriptionElement;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Parses {@link com.google.api.services.calendar.model.Event#description} data to the {@link com.kraluk.greminder.calendar.model.CalendarEvent} proper fields
 *
 * @author lukasz
 */
@UtilityClass
@Slf4j
final class DescriptionParser {
    static final String SPLIT_CHARACTER = "\\|";
    static final String DESCRIPTION_PATTERN = "%s|%s|%s";

    // title | leader | phone_number
    private static final Pattern DESCRIPTION_REGEX =
        Pattern.compile("^[a-zA-Z.\\s]+\\|[a-zA-Z.\\s]+\\|[0-9\\s]+$");

    /**
     * Parses given string and gets from it some data and puts it to a {@link CalendarEvent} object
     *
     * @param description a description string
     * @param event       an existing instance of an event object
     * @return a filled {@link CalendarEvent} with proper data
     */
    static CalendarEvent parse(final String description, final CalendarEvent event) {
        Objects.requireNonNull(description, "Description cannot be null!");
        Objects.requireNonNull(event, "Event cannot be null!");

        String trimed = description.trim();

        if (!DESCRIPTION_REGEX.matcher(trimed).matches()) {
            throw new IllegalDescriptionFormatException(
                String.format("Illegal description format! '%s'", trimed));
        }

        String[] splits = trimed.split(SPLIT_CHARACTER);

        event.setTitle(splits[DescriptionElement.TITLE].trim());
        event.setLeader(splits[DescriptionElement.LEADER].trim());
        event.setPhoneNumber(splits[DescriptionElement.PHONE_NUMBER].trim().replace(" ", ""));

        return event;
    }

    /**
     * Parses given string and gets from it some data and puts it to a {@link CalendarEvent} object
     *
     * @param description a description string
     * @return a completely new {@link CalendarEvent} instance already filled with some data
     */
    static CalendarEvent parse(final String description) {
        return parse(description, new CalendarEvent());
    }
}
