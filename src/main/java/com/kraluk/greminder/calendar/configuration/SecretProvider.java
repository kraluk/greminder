package com.kraluk.greminder.calendar.configuration;

import com.kraluk.greminder.common.exception.GreminderException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Loads to the application context Google API's secret
 *
 * @author lukasz
 */
@Component
@Slf4j
class SecretProvider {
    private static final String SECRET_PATH_PATTERN = "file:%s";

    private final ApplicationContext applicationContext;
    private final String secretPath;

    @Autowired
    SecretProvider(ApplicationContext applicationContext,
                   @Value("${calendar.dir.secret}") String secretPath) {
        this.applicationContext = applicationContext;
        this.secretPath = secretPath;
    }

    Resource getSecret() {
        log.info("Attempting to get secret from '{}'", secretPath);

        Resource secret =
            applicationContext.getResource(String.format(SECRET_PATH_PATTERN, secretPath));

        if (secret == null || !secret.exists()) {
            throw new SecretInitializationException(
                String.format("Unable to obtain secret from path '%s'", secretPath));
        }

        log.info("Successfully obtained resource.");

        return secret;
    }

    // --- Exception

    static class SecretInitializationException extends GreminderException {

        SecretInitializationException(String s) {
            super(s);
        }
    }
}
