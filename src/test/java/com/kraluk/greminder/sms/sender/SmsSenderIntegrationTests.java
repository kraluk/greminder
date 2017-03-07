package com.kraluk.greminder.sms.sender;

import com.google.common.base.Strings;
import com.kraluk.greminder.sms.exception.SmsSendingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.kraluk.greminder.util.AppProfile.TEST;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

/**
 * Test suite for class {@link SmsSender}
 *
 * @author lukasz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(TEST)
public class SmsSenderIntegrationTests {

    @Autowired
    private SmsSender smsSender;

    @Test
    public void testSendMessage() {
        String result = smsSender.send("0000", "0000");

        assertNotNull(result);
    }

    @Test
    public void testMessageAboveLimitWillNotBeSended() {
        String message = Strings.repeat("X", SmsSender.SMS_SIZE_LIMIT + 1);

        assertThatThrownBy(() -> smsSender.send("0000000", message))
            .isInstanceOf(SmsSendingException.class);
    }

    @Test
    public void testEmptyMessageWillNotBeSended() {
        assertThatThrownBy(() -> smsSender.send("0000000", null))
            .isInstanceOf(SmsSendingException.class);
    }

    @Test
    public void testCheckMessageLimitAndThrowAnException() {
        String message = Strings.repeat("X", SmsSender.SMS_SIZE_LIMIT + 1);

        assertThatThrownBy(() -> smsSender.checkMessageSize(message))
            .isInstanceOf(SmsSendingException.class);
    }
}