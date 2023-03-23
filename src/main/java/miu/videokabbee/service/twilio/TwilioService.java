package miu.videokabbee.service.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;

/**
 * Helps to send OTP through SMS
 *
 * */

@Service
public class TwilioService {

//   @Value("{twilio.accountSid}")
    private String accountSid= "ACc8952a46e76c33cab1269420ac726cc7";

   // @Value("{twilio.authToken}")
    private String authToken = "6e0d5fcfbf3615cc19117a816710ad95";

   //@Value("{twilio.fromPhoneNumber}")
    private String fromPhoneNumber = "+18775353749";

    public void sendSms(String toPhoneNumber, String message) {
        System.out.println("sms-method-called");
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber), message).create();
        System.out.println(message);
    }
}
