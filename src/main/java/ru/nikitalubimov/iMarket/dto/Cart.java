package ru.nikitalubimov.iMarket.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.nikitalubimov.iMarket.data.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Slf4j
public class Cart {

    private List<CartItem> cartItemList;
    private int totalPrice;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public List<CartItem> getCartItemList() {
        return Collections.unmodifiableList(cartItemList);
    }

    public void add(Product product) {
        for (CartItem item : cartItemList) {
            if (product.getId() == item.getProductId()) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                recalculate();
                return;
            }
        }
        cartItemList.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(), product.getCost()));
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice();
        }
    }

    public void changeQuantityPlus (Long id) {
        for (CartItem item : cartItemList) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
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
                    deleteItem(item.getProductId());
                    recalculate();
                    return;
                }
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
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
