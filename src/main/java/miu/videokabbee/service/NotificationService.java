package miu.videokabbee.service;

import jakarta.mail.MessagingException;
import miu.videokabbee.domain.PolicyUpdateNotification;

/**
 * NotificationService interface with a sendNotification method to define the contract for sending notifications:
 */
public interface NotificationService {
    void sendNotification(String recipient, PolicyUpdateNotification notification) throws MessagingException;
}
