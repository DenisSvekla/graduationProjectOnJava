package com.tms.service.impl;

import java.sql.Date;

import com.tms.exceprtionResolver.NotFoundException;
import com.tms.exceprtionResolver.OtherException;
import com.tms.model.domain.Subscription;
import com.tms.model.request.SubscriptionRequestDto;
import com.tms.model.response.subscription.SubscriptionResponseDto;
import com.tms.repository.SubscriptionRepository;
import com.tms.service.SubscriptionService;
import com.tms.utils.validation.service.CheckUserByIdInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tms.utils.ExceptionMessage.NO_ACCESS;
import static com.tms.utils.ExceptionMessage.SUBSCRIPTION_NOT_FOUND;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final long dayInMls = 86400000L;

    private final SubscriptionRepository subscriptionRepository;

    private final CheckUserByIdInService checkUserByIdInService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, CheckUserByIdInService checkUserByIdInService) {
        this.subscriptionRepository = subscriptionRepository;
        this.checkUserByIdInService = checkUserByIdInService;
    }

    public SubscriptionResponseDto getSubscription(int id) {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        if (checkUserByIdInService.checkUserByIdAndType(id)) {
            Subscription subscription = subscriptionRepository.findSubscriptionByUserId(id).orElseThrow(() -> new NotFoundException(SUBSCRIPTION_NOT_FOUND));
            var value = subscription.getExpireDate().compareTo(new Date(System.currentTimeMillis()));
            if (value < 0) {
                subscriptionResponseDto.setSubscriptionStatus("NO ACTIVE");
            }
            if (value >= 0) {
                subscriptionResponseDto.setSubscriptionStatus("Subscription active to " + subscription.getExpireDate().toString());
            }
            return subscriptionResponseDto;
        }
        throw new OtherException(NO_ACCESS);
    }

    @Transactional
    public Subscription createOrUpdateSubscription(int id, SubscriptionRequestDto subscriptionRequestDto) {
        if (checkUserByIdInService.checkUserByIdAndType(id)) {
            int value;
            Subscription subscription = subscriptionRepository.findSubscriptionByUserId(id).orElse(new Subscription());
            try {
                value = subscription.getExpireDate().compareTo(new Date(System.currentTimeMillis()));
            } catch (NullPointerException e) {
                value = -1;
                subscription.setUserId(id);
            }

            if (value < 0) {
                subscription.setExpireDate(new Date(System.currentTimeMillis() + dayInMls * subscriptionRequestDto.getCountDate()));
            }
            if (value >= 0) {
                subscription.setExpireDate(new Date(subscription.getExpireDate().getTime() + dayInMls * subscriptionRequestDto.getCountDate()));
            }
            return subscriptionRepository.save(subscription);
        }
        throw new OtherException(NO_ACCESS);
    }

    public Boolean deleteSubscription(int id) {
        Subscription subscription = subscriptionRepository.findSubscriptionByUserId(id).orElseThrow(()->new NotFoundException(SUBSCRIPTION_NOT_FOUND));
        int value;
        try {
            value = subscription.getExpireDate().compareTo((new Date(System.currentTimeMillis())));
        } catch (NullPointerException e) {
            value =-1;
            subscription.setUserId(id);
        }
        if (value<0) {
            return false;
        }
        if(value>=0) {
            subscription.setExpireDate(new Date(2000-00-00));
            subscriptionRepository.save(subscription);
            return true;
        }
        return false;
    }
}
