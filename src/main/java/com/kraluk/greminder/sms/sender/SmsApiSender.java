package com.kraluk.greminder.sms.sender;

import com.kraluk.greminder.sms.exception.SmsSendingException;
import com.kraluk.greminder.sms.util.SmsResponseUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.exception.SmsapiException;

import static com.kraluk.greminder.util.AppProfile.PRODUCTION;

/**
 * Provides SMS sending functionalities by SmsApi provider
 *
 * @author lukasz
 */
@Service
@Profile(PRODUCTION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class SmsApiSender implements SmsSender {
    private static final String SMS_TYPE = "ECO";
    private static final String SMS_TEMPLATE = "[Reminder] %s";

    private final SmsFactory smsFactory;

    @Override
    public String send(String to, String content) {
        try {
            log.info("Attempting to send a message to '{}'", to);

            String text = String.format(SMS_TEMPLATE, content);
            checkMessageSize(text);

            SMSSend action = smsFactory.actionSend()
                .setText(text)
                .setTo(to)
                .setSender(SMS_TYPE);

            SendStatusResponse response = action.execute();
            String status = SmsResponseUtils.getPrettyResponse(response);

            log.info("Message sended successfully with status '{}'", status);

            return status;

        } catch (SmsapiException e) {
            throw new SmsSendingException("Unable to send a Text Message!", e);
        }
    }
}