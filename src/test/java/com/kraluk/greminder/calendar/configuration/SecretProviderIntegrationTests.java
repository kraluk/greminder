package com.kraluk.greminder.calendar.configuration;

import com.kraluk.greminder.test.base.AbstractNonCalendarRelatedTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for class {@link SecretProvider}
 *
 * @author lukasz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SecretProviderIntegrationTests extends AbstractNonCalendarRelatedTests {

    @Autowired
    private ApplicationContext applicationContext;

    private SecretProvider secretProvider;
    private Path testFile;

    @Before
    public void setUp() throws IOException {
        testFile = Files.createTempFile(SecretProviderIntegrationTests.class.getSimpleName(),
            UUID.randomUUID().toString());

        secretProvider = new SecretProvider(applicationContext, testFile.toString());
    }

    @Test
    public void testGetSecret() throws IOException {
        Resource secret = secretProvider.getSecret();

        assertThat(secret).isNotNull().isInstanceOf(UrlResource.class);
        assertThat(secret.getFile().getAbsolutePath()).isEqualTo(testFile.toString());
    }
}
