package ru.nikitalubimov.iMarket.carts.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.nikitalubimov.iMarket.api.ProductDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Slf4j
public class Cart {

    private List<CartItem> cartItemList;
    private BigDecimal totalPrice;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void add(ProductDto product) {
        for (CartItem item : cartItemList) {
            if (product.getId() == item.getProductId()) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct().multiply(BigDecimal.valueOf(item.getQuantity())));
                recalculate();
                return;
            }
        }
        cartItemList.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(), product.getCost()));
        recalculate();
    }

    public void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cartItemList) {
            totalPrice = totalPrice.add(cartItem.getPrice());
        }
    }

    public void changeQuantityPlus (Long id) {
        for (CartItem item : cartItemList) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct().multiply(BigDecimal.valueOf(item.getQuantity())));
                recalculate();
                return;
            }
        }
    }

    public void changeQuantityMinus (Long id) {
        for (CartItem item : cartItemList) {
            if (item.getProductId().equals(id) & item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                if (item.getQuantity() == 0) {
                    cartItemList.remove(item);
                    recalculate();
                    return;
                }
                item.setPrice(item.getPricePerProduct().multiply(BigDecimal.valueOf(item.getQuantity())));
                recalculate();
                return;
            }
        }
    }

    public void deleteItem (Long id) {
        cartItemList.removeIf(cartItem -> cartItem.getProductId().equals(id));
        recalculate();
    }

    public void clearCart() {
        cartItemList.clear();
        recalculate();
    }
}
