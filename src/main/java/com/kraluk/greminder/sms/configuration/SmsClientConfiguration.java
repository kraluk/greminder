package com.kraluk.greminder.sms.configuration;

import com.kraluk.greminder.sms.exception.SmsConfigurationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.smsapi.Client;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.exception.ClientException;

import javaslang.control.Try;

/**
 * SMS Gateway Custom Client (REST-based) Configuration
 *
 * @author lukasz
 */
@Configuration
public class SmsClientConfiguration {

    @Bean
    public SmsFactory smsFactory(@Value("${sms.username}") String username,
                                 @Value("${sms.password}") String password) {

        return Try.of(() -> createSmsFactory(username, password))
            .getOrElseThrow(
                e -> new SmsConfigurationException("Unable to create SmsFactory!", e));
    }

    private static SmsFactory createSmsFactory(String username, String password)
        throws ClientException {
        Client client = new Client(username);
        client.setPasswordHash(password);

        return new SmsFactory(client);
    }
}