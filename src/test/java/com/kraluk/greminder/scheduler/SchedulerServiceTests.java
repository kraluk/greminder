package com.kraluk.greminder.scheduler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Test suite for class {@link SchedulerService}
 *
 * @author lukasz
 */
public class SchedulerServiceTests {

    private SchedulerService schedulerService;

    @Before
    public void setUp() {
        schedulerService = new SchedulerService();
    }

    @Test
    public void testRemind() {

        try {
            schedulerService.remind();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
