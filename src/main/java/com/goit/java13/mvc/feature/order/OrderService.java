package com.goit.java13.mvc.feature.order;

import com.goit.java13.mvc.feature.chocholate.price.ChocolatePriceService;
import com.goit.java13.mvc.feature.order.dto.CreateOrderRequest;
import com.goit.java13.mvc.feature.order.dto.CreateOrderResponse;
import com.goit.java13.mvc.feature.order.dto.OrderStateResponse;
import com.goit.java13.mvc.feature.order.status.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;
    private final ChocolatePriceService priceService;
    private final OrderStatusRepository statusRepository;
    private final OrderStatusService orderStatusService;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CreateOrderResponse create(CreateOrderRequest request) {
        Order order = new Order();
        order.setChocolateType(request.getType());
        order.setQuantity(request.getQuantity());
        order.setPricePerItem(priceService.getPrice(request.getType()));
        order.setCustomerEmail(request.getEmail());
        order.setDeliveryAddress(request.getAddress());

        repository.save(order);

        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderId(order.getId());
        response.setPrice(order.getPricePerItem() * order.getQuantity());

        orderStatusService.addStatus(order.getId(), OrderStatus.Status.awaitingProcessing);

        return response;
    }

    public OrderStateResponse getOrderState(String orderId) {
        List<OrderStatus> statuses = statusRepository.getStatusesForOrder(orderId);

        OrderStateResponse response = new OrderStateResponse();
        response.setStatuses(OrderStatusDto.from(statuses));

        return response;
    }

    public ProblematicOrdersResponse getProblematicOrders() {
        String sql =
                "WITH order_statuses AS (\n" +
                " SELECT order_id, status, max(created_at)\n" +
                "  FROM order_status\n" +
                "  WHERE status = 'awaitingProcessing'\n" +
                "GROUP BY order_id\n" +
                "ORDER BY created_at)\n" +
                "\n" +
                "SELECT order_id \n" +
                "FROM order_status\n" +
                "WHERE created_at <= NOW() - INTERVAL '10' SECOND AND order_id IN (\n" +
                " SELECT order_id FROM order_statuses\n" +
                ");\n" +
                "\n";

        List<String> ids = jdbcTemplate.queryForList(sql, Collections.emptyMap(), String.class);

        ProblematicOrdersResponse response = new ProblematicOrdersResponse();
        response.setIds(ids);
        return response;
    }


}
