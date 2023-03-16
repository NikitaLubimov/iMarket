package ru.nikitalubimov.iMarket.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.api.CartDto;
import ru.nikitalubimov.iMarket.carts.model.Cart;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto toDto (Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setCartItemList(cart.getCartItemList().stream().map(cartItemConverter::toDto).collect(Collectors.toList()));
        return cartDto;
    }
}
