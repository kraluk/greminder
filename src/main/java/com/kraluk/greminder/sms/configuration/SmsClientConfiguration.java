package com.kraluk.greminder.sms.configuration;

import com.kraluk.greminder.sms.exception.SmsConfigurationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.smsapi.Client;
import pl.smsapi.api.SmsFactory;

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
        SmsFactory smsFactory;

        try {
            Client client = new Client(username);
            client.setPasswordHash(password);

            smsFactory = new SmsFactory(client);
            return smsFactory;
        } catch (Exception e) {
            throw new SmsConfigurationException("Unable to create SmsFactory!", e);
        }
    }
}