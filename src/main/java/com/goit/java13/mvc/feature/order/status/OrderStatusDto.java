package com.goit.java13.mvc.feature.order.status;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class OrderStatusDto {
    private OrderStatus.Status status;
    private LocalDateTime createdAt;

    public static List<OrderStatusDto> from(List<OrderStatus> statuses) {
        return statuses.stream().map(OrderStatusDto::from).collect(Collectors.toList());
    }

    public static OrderStatusDto from(OrderStatus status) {
        return OrderStatusDto.builder().status(status.getStatus()).createdAt(status.getCreatedAt()).build();
    }
}
