package com.kraluk.greminder.sms.util;

import org.json.JSONArray;
import org.junit.Test;

import pl.smsapi.api.response.SendStatusResponse;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test suite for class {@link SmsResponseUtils}
 *
 * @author lukasz
 */
public class SmsResponseUtilsTests {

    private static final String
        EXAMPLE_RESPONSE =
        "[{\"number\" : 1, \"status\" : 1, \"points\" : 1}, {\"number\" : 2, \"status\" : 2, \"points\" : 2}]";

    @Test
    public void testValidateWithCorrectAddress() {
        SendStatusResponse response = new SendStatusResponse(1, 1, new JSONArray(EXAMPLE_RESPONSE));

        String result = SmsResponseUtils.getPrettyResponse(response);

        assertThat(result, is("Number: '1', Status: '1'; Number: '2', Status: '2'"));
    }
}