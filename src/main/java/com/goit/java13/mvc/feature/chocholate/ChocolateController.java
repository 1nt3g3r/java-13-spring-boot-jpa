package com.goit.java13.mvc.feature.chocholate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chocolate")
public class ChocolateController {
    private final ChocolateService chocolateService;

    @GetMapping("/list")
    public List<ChocolateDto> list() {
        return ChocolateDto.from(chocolateService.findAll());
    }

    @PostMapping("/create")
    public ModifyResponse create(@RequestBody ChocolateDto dto) {
        if (!dto.isTypeValid()) {
            return ModifyResponse.failed("type can't be null or empty");
        }

        chocolateService.createFromDto(dto);

        return ModifyResponse.success("created successfully");
    }

    @PostMapping("/update")
    public ModifyResponse update(@RequestBody ChocolateDto dto) {
        if (!dto.isTypeValid()) {
            return ModifyResponse.failed("type can't be null or empty");
        }

        if (chocolateService.exists(dto.getType())) {
            chocolateService.updateFromDto(dto);
            return ModifyResponse.success("updated successfully");
        } else {
            return ModifyResponse.failed("chocolate with type <" + dto.getType() + " doesn't exist");
        }
    }

    @PostMapping("/delete")
    public ModifyResponse delete(@RequestBody DeleteRequest request) {
        if (chocolateService.exists(request.getType())) {
            chocolateService.delete(request.getType());
            return ModifyResponse.success("successfully deleted");
        } else {
            return ModifyResponse.failed("chocolate with type <" + request.getType() + "> doesn't exist");
        }
    }
}
