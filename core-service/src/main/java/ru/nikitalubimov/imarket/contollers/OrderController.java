package ru.nikitalubimov.iMarket.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.api.OrderDto;
import ru.nikitalubimov.iMarket.converters.OrderConverter;
import ru.nikitalubimov.iMarket.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createdOrder(@RequestHeader String userName) {
        return orderService.createdOrder(userName);
    }


    @GetMapping
    public List<OrderDto> getOrders(@RequestHeader String userName) {
        return orderService.getCurrentOrder(userName).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
