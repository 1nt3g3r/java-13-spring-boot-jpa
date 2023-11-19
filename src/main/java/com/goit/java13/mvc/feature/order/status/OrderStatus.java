package com.goit.java13.mvc.feature.order.status;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@IdClass(OrderStatus.PK.class)
@Entity
public class OrderStatus {
    @Id
    private String orderId;

    @Enumerated(EnumType.STRING)
    @Id
    private Status status;

    @Id
    private LocalDateTime createdAt;

    @Data
    public static class PK implements Serializable {
        private String orderId;
        private Status status;
        private LocalDateTime createdAt;
    }

    public enum Status {
        awaitingProcessing,
        processing,
        shipped,
        received
    }
}
