package ru.nikitalubimov.iMarket.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.api.ProductDto;
import ru.nikitalubimov.iMarket.api.ResourceNotFoundException;
import ru.nikitalubimov.iMarket.carts.integrations.ProductServiceIntegration;
import ru.nikitalubimov.iMarket.carts.model.Cart;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private Cart tempCart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    private void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId).orElseThrow(() -> new ResourceNotFoundException("Product for cart not found, id: " + productId));
        tempCart.add(product);
    }

    public void changeQuantityPlus (Long id) {
       tempCart.changeQuantityPlus(id);
    }

    public void changeQuantityMinus (Long id) {
        tempCart.changeQuantityMinus(id);
    }


    public void deleteItemCart(Long id) {
        tempCart.deleteItem(id);
    }

    public void clearCart() {
        tempCart.clearCart();
    }
}
