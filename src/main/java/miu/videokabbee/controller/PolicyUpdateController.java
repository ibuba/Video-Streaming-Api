package miu.videokabbee.controller;

import jakarta.mail.MessagingException;
import miu.videokabbee.domain.PolicyEmailMessage;
import miu.videokabbee.service.policynotification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/policy_update")
public class PolicyUpdateController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity sendPolicyUpdateNotification(@RequestBody PolicyEmailMessage policyEmailMessage) throws MessagingException {

        notificationService.sendNotification(policyEmailMessage.getTo(), policyEmailMessage.getSubject(), policyEmailMessage.getMessage(), policyEmailMessage.getDate());
        return ResponseEntity.ok("Successfully sent!");
    }

}

