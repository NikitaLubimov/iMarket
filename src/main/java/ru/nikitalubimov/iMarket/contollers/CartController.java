package ru.nikitalubimov.iMarket.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public void addProductCart(@RequestBody Product product){
        cartService.addProductCart(product);
    }
}
