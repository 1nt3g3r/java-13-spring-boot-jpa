package com.goit.java13.mvc.feature.order.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String type;
    private int quantity;
    private String email;
    private String address;
}
