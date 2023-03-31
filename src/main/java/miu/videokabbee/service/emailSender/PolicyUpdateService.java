package miu.videokabbee.service.emailSender;

import jakarta.mail.MessagingException;
import miu.videokabbee.domain.PolicyUpdateNotification;
import miu.videokabbee.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * PolicyUpdateService class to handle retrieving the latest policy information and notifying users
 */
@Service
public class PolicyUpdateService {

    @Autowired
    private NotificationService notificationService;


    public void notifyUsersOfPolicyUpdate(String policyVersion) throws MessagingException {
        // Retrieve a list of all users who have opted in to receive policy update notifications
        List<String> users = getUserList();

        // Loop through each user and send a notification
        for (String user : users) {
            if (!hasAgreedToPolicy(user, policyVersion)) {
                PolicyUpdateNotification notification = new PolicyUpdateNotification("Dear Customer ", "\n\nThe platform's security and privacy policies have been updated. Please review the latest policies and agree to continue using the platform.", LocalDate.now());
                notificationService.sendNotification(user, notification);
            }
        }
    }

    private List<String> getUserList() {
        // retrieve a list of all users who have opted in to receive policy update notifications
        //Arrays.asList("user1@example.com", "user2@example.com", "user3@example.com");
        return Arrays.asList();
    }

    private boolean hasAgreedToPolicy(String user, String currentPolicyVersion) {
        // Code to retrieve the user's agreement status from a database or other storage
        String agreedPolicyVersion = "1.2.2"; // Example value retrieved from storage
        return agreedPolicyVersion.equals(currentPolicyVersion);
    }
}
