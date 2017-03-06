package com.kraluk.greminder;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Test suite for class {@link GreminderApplication}
 *
 * @author lukasz
 */
public class GreminderApplicationTests {

    @Test
    public void testContextLoads() {
        try {
            GreminderApplication.main("--spring.profiles.active=test");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
