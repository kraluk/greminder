package com.kraluk.greminder.util;

import lombok.experimental.UtilityClass;

/**
 * Provides Application Profiles' names
 *
 * @author lukasz
 */
@UtilityClass
public final class AppProfile {

    public static final String DEVELOPMENT = "dev";

    public static final String TEST = "test";

    public static final String PRODUCTION = "prod";

    /**
     * Application's features
     */
    public static class AppFeature {

        public static final String SCHEDULER = "scheduler";

        public static final String SCHEDULER_DISABLED = "scheduler-disabled";

        public static final String CALEDAR = "calendar";

        public static final String MESSAGING = "messaging";
    }
}
