package ru.nikitalubimov.iMarket.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nikitalubimov.iMarket.api.CartDto;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    @Value(value = "${url.pathCart}")
    private String contextPathCartsService;

    public Optional<CartDto> getCurrentCart(){
        return Optional.ofNullable(restTemplate.getForObject(contextPathCartsService + "/cart", CartDto.class));
    }
}
