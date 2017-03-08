package com.kraluk.greminder.sms.sender;

import com.kraluk.greminder.sms.exception.SmsSendingException;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.exception.SmsapiException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for class {@link SmsApiSender}
 *
 * @author lukasz
 */
public class SmsApiSenderTests {

    private SMSSend action;

    private SmsSender smsSender;

    @Before
    public void setUp() throws SmsapiException {
        SmsFactory smsFactory = mock(SmsFactory.class);
        action = mock(SMSSend.class);

        when(smsFactory.actionSend()).thenReturn(action);
        when(action.setText(anyString())).thenReturn(action);
        when(action.setTo(anyString())).thenReturn(action);
        when(action.setSender(anyString())).thenReturn(action);

        smsSender = new SmsApiSender(smsFactory);
    }

    @Test
    public void testSend() throws SmsapiException {
        when(action.execute()).thenReturn(generateResponse());

        String result = smsSender.send("123456789", "example content");

        assertThat(result).isNotEmpty();
    }

    @Test
    public void testSendAndThrowException() throws SmsapiException {
        when(action.execute()).thenThrow(SmsapiException.class);

        assertThatThrownBy(() -> smsSender.send("123", "123"))
            .isInstanceOf(SmsSendingException.class);
    }

    private static SendStatusResponse generateResponse() {
        return new SendStatusResponse(1, 1,
            new JSONArray("[{number : '123', status : 'OK', points : '1'}]"));
    }
}
