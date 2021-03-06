package com.kraluk.greminder.util.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.kraluk.greminder.util.AppProfile.DEVELOPMENT;
import static com.kraluk.greminder.util.AppProfile.PRODUCTION;

/**
 * Greminder's bootstrap service which checks basic preconditions required to start the application
 *
 * @author lukasz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class GreminderBootstrapService implements InitializingBean {

    private final Environment environment;

    @Override
    public void afterPropertiesSet() throws Exception {
        performProfilesCheck();
    }

    private void performProfilesCheck() {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());

        log.info("Active profiles '{}'", profiles);

        boolean result =
            profiles.contains(DEVELOPMENT) && profiles.contains(PRODUCTION);

        if (result) {
            throw new IllegalStateException(
                String.format("Its illegal to start application with both '%s' and '%s' profiles!",
                    DEVELOPMENT, PRODUCTION));
        }

        if (!profiles.contains(PRODUCTION)) {
            log.warn("Application is running in a NON production profile.");
        }
    }
}
