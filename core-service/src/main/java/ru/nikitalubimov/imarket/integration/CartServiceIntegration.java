package ru.nikitalubimov.iMarket.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nikitalubimov.iMarket.api.CartDto;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<CartDto> getCurrentCart(){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8090/app-carts/api/v1/cart", CartDto.class));
    }
}
