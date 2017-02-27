package com.kraluk.greminder.util.bootstrap;

import com.kraluk.greminder.util.AppProfile;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for class {@link GreminderBootstrapService}
 *
 * @author lukasz
 */
public class GreminderBootstrapServiceTests {

    private Environment environment;
    private GreminderBootstrapService bootstrapService;

    @Before
    public void setUp() {
        environment = mock(Environment.class);
        bootstrapService = new GreminderBootstrapService(environment);
    }

    @Test
    public void testProperProductionProfilesCheck() {
        when(environment.getActiveProfiles())
            .thenReturn(new String[]{AppProfile.PRODUCTION});

        try {
            bootstrapService.afterPropertiesSet();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testProperDevelopmentProfilesCheck() {
        when(environment.getActiveProfiles())
            .thenReturn(new String[]{AppProfile.DEVELOPMENT});

        try {
            bootstrapService.afterPropertiesSet();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testEmptyProfilesCheck() {
        when(environment.getActiveProfiles())
            .thenReturn(new String[]{});

        try {
            bootstrapService.afterPropertiesSet();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testIllegalProfilesCombinationCheck() {
        when(environment.getActiveProfiles())
            .thenReturn(new String[]{AppProfile.PRODUCTION, AppProfile.DEVELOPMENT});

        assertThatThrownBy(() -> bootstrapService.afterPropertiesSet())
            .isInstanceOf(IllegalStateException.class);
    }
}
