package com.tms.controller;

import com.tms.model.request.SubscriptionRequestDto;
import com.tms.model.response.subscription.SubscriptionResponseDto;
import com.tms.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{id}/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable int id) {
        return new ResponseEntity<>(subscriptionService.getSubscription(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSubscriptionOrUpdate(@PathVariable int id, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return new ResponseEntity<>
                (subscriptionService.createOrUpdateSubscription(id, subscriptionRequestDto) != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
