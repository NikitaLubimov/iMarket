package ru.nikitalubimov.iMarket.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nikitalubimov.iMarket.api.CartDto;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartSeviceWebClient;

    public CartDto getCurrentCart(String userName) {
        return cartSeviceWebClient.get()
                .uri("/cart/0")
                .header("username", userName)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void cartClear(String userName) {
        cartSeviceWebClient.delete()
                .uri("/0/clearCart")
                .header("username", userName)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
