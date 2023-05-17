package com.tms.repository;

import java.util.Optional;

import com.tms.model.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findSubscriptionByUserId(Integer integer);
}
