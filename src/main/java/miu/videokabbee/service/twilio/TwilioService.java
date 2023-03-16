package miu.videokabbee.service.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;

@Service
public class TwilioService {

  //  @Value("${twilio.accountSid}")
    private String accountSid= "ACc8952a46e76c33cab1269420ac726cc7";

   // @Value("${twilio.authToken}")
    private String authToken ="a6f785c98dd99d866316b01115339a3f";

   // @Value("${twilio.fromPhoneNumber}")
    private String fromPhoneNumber="+18775353749";

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message).create();
    }
}
