package ru.nikitalubimov.iMarket.carts.converters;

import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.api.CartItemDto;
import ru.nikitalubimov.iMarket.carts.model.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto toDto (CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto(
                cartItem.getProductId(),
                cartItem.getProductTitle(),
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
        );
        return cartItemDto;
    }
}
