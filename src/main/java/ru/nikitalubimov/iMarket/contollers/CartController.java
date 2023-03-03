package ru.nikitalubimov.iMarket.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.dto.Cart;
import ru.nikitalubimov.iMarket.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
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
