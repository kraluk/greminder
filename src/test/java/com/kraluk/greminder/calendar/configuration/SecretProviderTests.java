package com.kraluk.greminder.calendar.configuration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for class {@link SecretProvider}
 *
 * @author lukasz
 */
public class SecretProviderTests {
    private static final String DUMMY_PATH = "/dummy/path";

    private ApplicationContext applicationContext;
    private Resource resource;

    private SecretProvider secretProvider;

    @Before
    public void setUp() {
        applicationContext = mock(ApplicationContext.class);
        resource = mock(Resource.class);

        when(applicationContext.getResource(anyString())).thenReturn(resource);

        secretProvider = new SecretProvider(applicationContext, DUMMY_PATH);
    }

    @Test
    public void testGetSecret() {
        when(resource.exists()).thenReturn(true);

        Resource secret = secretProvider.getSecret();

        assertThat(secret).isNotNull();
    }

    @Test
    public void testGetSecretAndThrowExceptionDueToNegativeResourceCheck() {
        when(resource.exists()).thenReturn(false);

        assertThatThrownBy(() -> secretProvider.getSecret())
            .isInstanceOf(SecretProvider.SecretInitializationException.class);
    }

    @Test
    public void testGetSecretAndThrowExceptionDueToNullifiedResource() {
        when(applicationContext.getResource(anyString())).thenReturn(null);

        assertThatThrownBy(() -> secretProvider.getSecret())
            .isInstanceOf(SecretProvider.SecretInitializationException.class);
    }
}
