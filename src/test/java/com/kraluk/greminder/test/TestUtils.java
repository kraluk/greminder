package com.kraluk.greminder.test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provides some useful constants and methods for tests
 *
 * @author lukasz
 */
public final class TestUtils {

    public static final String TEST_PROPERTIES = "application-test";

    public static final String MESSAGE_TEMPLATE_KEY = "sms.message.template";

    public static final ResourceBundle TEST_BUNDLE =
        ResourceBundle.getBundle(TEST_PROPERTIES, Locale.getDefault());

    private TestUtils() {
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated!");
    }

    public static String getTestProperty(String key) {
        return TEST_BUNDLE.getString(key);
    }
}
