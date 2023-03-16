package ru.nikitalubimov.iMarket.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nikitalubimov.iMarket.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    @Value(value = "${url.pathProducts}")
    private String contextPathProductsService;

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(contextPathProductsService + "/products/getProduct/" + id, ProductDto.class));
    }
}
