package com.goit.java13.mvc.feature.order.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderStatusService {
    private final OrderStatusRepository repository;

    public void addStatus(String orderId, OrderStatus.Status status) {
        OrderStatus newStatus = new OrderStatus();
        newStatus.setOrderId(orderId);
        newStatus.setStatus(status);
        newStatus.setCreatedAt(LocalDateTime.now());

        repository.save(newStatus);
    }
}
