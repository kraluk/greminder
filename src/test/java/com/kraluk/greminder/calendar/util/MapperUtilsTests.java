package com.kraluk.greminder.calendar.util;

import com.google.api.client.util.DateTime;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for class {@link MapperUtils}
 *
 * @author lukasz
 */
public class MapperUtilsTests {
    private static final String REFERENCE_DATE = "2011-11-11";
    private static final String EXPECTED_DATE = "2011-11-11T01:00";

    @Test
    public void testShouldProperlyMap() {
        DateTime dateTime = new DateTime(REFERENCE_DATE);

        LocalDateTime result = MapperUtils.convert(dateTime);

        assertThat(result.toString()).isEqualTo(EXPECTED_DATE);
    }
}
