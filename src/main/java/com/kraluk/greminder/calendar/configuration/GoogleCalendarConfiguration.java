package com.kraluk.greminder.calendar.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.kraluk.greminder.util.AppProfile;
import com.kraluk.greminder.util.Version;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * Google Calendar Configuration class
 *
 * @author lukasz
 */
@Configuration
@Profile(AppProfile.PRODUCTION)
@Slf4j
public class GoogleCalendarConfiguration {
    private static final String ACCESS_TYPE = "offline";

    private static final List<String> SCOPES
        = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    @Bean
    static JsonFactory jsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }

    @Bean
    static HttpTransport httpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    static FileDataStoreFactory fileDataStoreFactory(
        @Value("${calendar.dir.datastore}") String dataStoreDir)
        throws IOException {
        log.debug("Creating data store for directory '{}'...", dataStoreDir);
        return new FileDataStoreFactory(Paths.get(dataStoreDir).toFile());
    }

    @Bean
    static Credential credential(HttpTransport httpTransport,
                                 FileDataStoreFactory fileDataStoreFactory,
                                 JsonFactory jsonFactory,
                                 SecretProvider secretProvider,
                                 @Value("${calendar.user}") String user)
        throws IOException {

        log.debug("Attempting to load Google API's secrets...");

        InputStream secretStream = secretProvider.getSecret().getInputStream();
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(jsonFactory, new InputStreamReader(secretStream));

        log.debug("Secrets loaded successfully.");

        GoogleAuthorizationCodeFlow flow =
            new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(fileDataStoreFactory)
                .setAccessType(ACCESS_TYPE)
                .build();

        log.debug("Attempting to create Google Credentials...");

        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize(user);

        log.debug("Credentials created successfully.");

        return credential;
    }

    @Bean
    public static Calendar calendar(HttpTransport httpTransport, Credential credential,
                                    JsonFactory jsonFactory) {
        return new Calendar.Builder(
            httpTransport, jsonFactory, credential)
            .setApplicationName(Version.NAME)
            .build();
    }
}
