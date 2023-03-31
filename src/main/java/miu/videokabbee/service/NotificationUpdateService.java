package miu.videokabbee.service;

import jakarta.mail.MessagingException;
import miu.videokabbee.service.emailSender.PolicyUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * *trigger the policy update notification by calling the notifyUsersOfPolicyUpdate method on the PolicyUpdateService class
 * The code to check for updates is used to determine if there are any changes or updates to the platform's security and privacy policies.
 * This code can be used to periodically check for updates and notify users if there are any changes that may affect their personal data.
 * By implementing this code, users can stay informed and aware of any changes to the policies that govern the use of their personal data on the platform.
 * This helps to maintain trust and transparency with users, and ensures that their data is being handled in a responsible and secure manner.
 */
@Service
public class NotificationUpdateService {
    @Autowired
    private PolicyUpdateService policyUpdateService;

    public void someMethod() throws MessagingException {
        // Code to check for updates to the platform's security and privacy policies
        String latestPolicyVersion = "1.2.3";

        policyUpdateService.notifyUsersOfPolicyUpdate(latestPolicyVersion);
    }
}
