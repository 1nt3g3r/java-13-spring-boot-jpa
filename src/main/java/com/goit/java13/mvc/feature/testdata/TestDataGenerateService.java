package com.goit.java13.mvc.feature.testdata;

import com.goit.java13.mvc.feature.chocholate.Chocolate;
import com.goit.java13.mvc.feature.chocholate.ChocolateRepository;
import com.goit.java13.mvc.feature.order.Order;
import com.goit.java13.mvc.feature.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class TestDataGenerateService {
    private final ChocolateRepository chocolateRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        orderRepository.deleteAll();
        chocolateRepository.deleteAll();

        Random random = new Random();

        List<String> baseChocolateTypes = Arrays.asList("white", "black");

        List<String> chocolateTypeIds = new ArrayList<>();

        //Generate chocolate types
        for(int i = 0; i < 10; i++) {
            String baseChocolateType = baseChocolateTypes.get(
                    random.nextInt(baseChocolateTypes.size())
            );
            int chocolateTypeValue = random.nextInt(100);
            String finalChocolateId = baseChocolateType + chocolateTypeValue;

            chocolateTypeIds.add(finalChocolateId);

            Chocolate chocolate = new Chocolate();
            chocolate.setType(finalChocolateId);
            chocolateRepository.save(chocolate);
        }

        //Generate orders
        for(int i = 0; i < 1000; i++) {
            Order order = new Order();
            order.setChocolateType(chocolateTypeIds.get(
                    random.nextInt(chocolateTypeIds.size())
            ));
            order.setQuantity(
                    1 + random.nextInt(20)
            );
            order.setPricePerItem(
                    100 + random.nextInt(500)
            );
            order.setCustomerEmail("email@mail.com");
            order.setDeliveryAddress("delivery.address");

            orderRepository.save(order);
        }
    }
}
