package com.goit.java13.mvc.feature.order.dto;

import com.goit.java13.mvc.feature.order.status.OrderStatus;
import com.goit.java13.mvc.feature.order.status.OrderStatusDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderStateResponse {
    private List<OrderStatusDto> statuses;
}
