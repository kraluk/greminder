package com.kraluk.greminder.scheduler;

import com.kraluk.greminder.calendar.CalendarService;
import com.kraluk.greminder.sms.message.MessageBuilder;
import com.kraluk.greminder.sms.sender.SmsSender;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.kraluk.greminder.util.AppProfile.TEST;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Test suite for class {@link SchedulerService}
 *
 * @author lukasz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(TEST)
public class SchedulerServiceIntegrationTests {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private MessageBuilder messageBuilder;

    private SchedulerService schedulerService;

    @Before
    public void setUp() {
        schedulerService = spy(new SchedulerService(calendarService, smsSender, messageBuilder));
    }

    @Test
    public void testRemind() {

        try {
            schedulerService.remind();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(schedulerService, atLeast(1)).remind();
    }
}
