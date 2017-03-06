package com.kraluk.greminder.sms.sender;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.kraluk.greminder.util.AppProfile.DEVELOPMENT;
import static com.kraluk.greminder.util.AppProfile.TEST;

/**
 * Dummy SmsSender implementation for the development and test purposes.
 * <br/>
 * Uses only *logger* for "sending" messages.
 *
 * @author lukasz
 */
@Service
@Profile({DEVELOPMENT, TEST})
@Slf4j
class LogSmsSender implements SmsSender {

    @PostConstruct
    public void init() {
        log.warn("DEV ONLY: dummy instance created.");
    }

    @Override
    public String send(String to, String content) {
        log.info("Sending the text message to '{}' with content '{}'...", to, content);

        checkMessageSize(content);

        log.info("Message sended successfully.");
        return "MESSAGE SENT";
    }
}