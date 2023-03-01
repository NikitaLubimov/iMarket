package ru.nikitalubimov.iMarket.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Product> getProductCart (){
        return cartService.getProductList();
    }

    @GetMapping("/add/{id}")
    public void addProductCart(@PathVariable Long id) {
        cartService.addProductCart(id);
    }

    @DeleteMapping("/{id}")
    public void delProductCart(@PathVariable Long id) {
        cartService.deleteProductCart(id);
    }
}
