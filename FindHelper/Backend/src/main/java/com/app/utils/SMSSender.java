package com.app.utils;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSSender {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSMS(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioPhoneNumber),
                messageBody
        ).create();
        System.out.println("Sent message SID: " + message.getSid());
    }
}
