package ru.nikitalubimov.iMarket.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.api.CartDto;
import ru.nikitalubimov.iMarket.carts.converters.CartConverter;
import ru.nikitalubimov.iMarket.carts.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.toDto(cartService.getCurrentCart());
    }

    @PostMapping("/{id}")
    public void addProductToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping("/changePlus/{id}")
    public void changeQuantityPlus(@PathVariable Long id) {
        cartService.changeQuantityPlus(id);
    }

    @GetMapping("/changeMinus/{id}")
    public void changeQuantityMinus(@PathVariable Long id) {
        cartService.changeQuantityMinus(id);
    }

    @DeleteMapping("/{id}")
    public void delProductCart(@PathVariable Long id) {
        cartService.deleteItemCart(id);
    }

    @DeleteMapping("/clearCart")
    public void clearCart() {
        cartService.clearCart();
    }
}
