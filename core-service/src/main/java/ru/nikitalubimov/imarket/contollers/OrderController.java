package ru.nikitalubimov.iMarket.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.api.ResourceNotFoundException;
import ru.nikitalubimov.iMarket.data.User;
import ru.nikitalubimov.iMarket.services.OrderService;
import ru.nikitalubimov.iMarket.services.UserService;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createdOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User не найден"));
        orderService.createdOrder(user);
    }
}
