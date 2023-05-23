package com.tms.service;

import com.tms.model.domain.Subscription;
import com.tms.model.request.SubscriptionRequestDto;
import com.tms.model.response.subscription.SubscriptionResponseDto;

public interface SubscriptionService {

    SubscriptionResponseDto getSubscription(int id);

    Subscription createOrUpdateSubscription(int id, SubscriptionRequestDto subscriptionRequestDto);

    Boolean deleteSubscription(int id);
}
