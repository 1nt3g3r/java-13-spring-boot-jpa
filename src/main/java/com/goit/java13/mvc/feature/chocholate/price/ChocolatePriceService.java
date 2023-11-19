package com.goit.java13.mvc.feature.chocholate.price;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChocolatePriceService {
    public int getPrice(String chocolateType) {
        Random random = new Random();
        return 5 + random.nextInt(6);
    }
}
