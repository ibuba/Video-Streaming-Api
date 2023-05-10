package miu.videokabbee.service.subscription;

import miu.videokabbee.domain.Subscription;
import miu.videokabbee.repository.SubscriptionRepository;
import miu.videokabbee.service.paypal.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

   @Autowired
   private SubscriptionRepository subscriptionRepository;

   @Autowired
   private PaypalService paypalService;




    @Override
    public Subscription subscribe(Subscription subscription, PaypalService paymentDetails) {
        return null;
    }

    @Override
    public Subscription getSubscription(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).orElse(null);
    }

    @Override
    public void cancelSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);

    }
}
