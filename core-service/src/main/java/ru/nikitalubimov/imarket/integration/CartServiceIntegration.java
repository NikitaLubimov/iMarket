package ru.nikitalubimov.iMarket.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nikitalubimov.iMarket.api.CartDto;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartSeviceWebClient;

    public CartDto getCurrentCart() {
        return cartSeviceWebClient.get()
                .uri("/cart")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void cartClear() {
        cartSeviceWebClient.delete()
                .uri("/clearCart")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
