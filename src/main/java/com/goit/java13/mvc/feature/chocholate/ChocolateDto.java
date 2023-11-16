package com.goit.java13.mvc.feature.chocholate;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class ChocolateDto {
    private String type;
    private String description;

    public boolean isTypeValid() {
        return type != null && !type.isBlank();
    }

    public static List<ChocolateDto> from(Iterable<Chocolate> chocolates) {
        List<ChocolateDto> result = new ArrayList<>();

        for (Chocolate chocolate : chocolates) {
            result.add(from(chocolate));
        }

        return result;
    }

    public static ChocolateDto from(Chocolate chocolate) {
        return ChocolateDto
                .builder()
                .type(chocolate.getType())
                .description(chocolate.getDescription())
                .build();
    }
}
