package com.goit.java13.mvc.feature.chocholate;

import com.goit.java13.mvc.feature.security.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chocolate")
public class ChocolateController {
    private final ChocolateService chocolateService;
    private final PrincipalService principalService;
    private final UserDetailsService userDetailsService;

    @PreAuthorize("hasRole('SUPERUSER')")
    @GetMapping("/list")
    public List<ChocolateDto> list() {

        System.out.println("principalService.getUsername() = " + principalService.getUsername());
        System.out.println("principalService.getRoles() = " + principalService.getRoles());

        System.out.println("userDetailsService.getClass() = " + userDetailsService.getClass());

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
