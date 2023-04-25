package ru.nikitalubimov.iMarket.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.api.ProductDto;
import ru.nikitalubimov.iMarket.carts.integrations.ProductServiceIntegration;
import ru.nikitalubimov.iMarket.carts.model.Cart;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid,cart -> cart.add(product));
    }

    public void changeQuantityPlus(String uuid, Long id) {
        execute(uuid,cart -> cart.changeQuantityPlus(id));
    }

    public void changeQuantityMinus(String uuid, Long id) {
        execute(uuid,cart -> cart.changeQuantityMinus(id));
    }


    public void deleteItemCart(String uuid, Long id) {
        execute(uuid,cart -> cart.deleteItem(id));
    }

    public void clearCart(String uuid) {
        execute(uuid,Cart::clearCart);
    }

    public void execute (String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid,cart);
    }
}
