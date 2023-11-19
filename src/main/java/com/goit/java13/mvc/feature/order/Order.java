package com.goit.java13.mvc.feature.order;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "chocolate_order")
public class Order {
    @Id
    private String id = UUID.randomUUID().toString();

    private String chocolateType;

    private int quantity;

    private int pricePerItem;

    private String customerEmail;

    private String deliveryAddress;
}
