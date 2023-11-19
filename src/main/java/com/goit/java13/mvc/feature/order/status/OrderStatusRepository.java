package com.goit.java13.mvc.feature.order.status;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, OrderStatus.PK> {
    @Query("from OrderStatus s WHERE s.orderId = :orderId")
    List<OrderStatus> getStatusesForOrder(String orderId);
}
