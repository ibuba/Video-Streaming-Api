package miu.videokabbee.controller;

import miu.videokabbee.domain.Subscription;
import miu.videokabbee.service.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;


    @GetMapping("/{subscriptionId}")
    public Subscription getSubscription(@PathVariable Long subscriptionId) {
        Subscription subscription = subscriptionService.getSubscription(subscriptionId);
        return subscription;
    }

    @PostMapping("/{subscriptionId}/cancel")
    public void cancelSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
    }

}
