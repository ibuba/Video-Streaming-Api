package miu.videokabbee.service.subscription;

import com.paypal.base.rest.PayPalModel;
import miu.videokabbee.domain.Subscription;
import miu.videokabbee.service.paypal.PaypalService;

import java.util.List;

public interface SubscriptionService {
    Subscription subscribe(Subscription subscription, PaypalService paymentDetails);
    Subscription getSubscription(Long subscriptionId);
//    List<Subscription> getAllSubscriptionsForUser(Long userId);
    void cancelSubscription(Long subscriptionId);
}
