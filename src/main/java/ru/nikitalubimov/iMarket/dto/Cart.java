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
        cartItemList.add(new CartItem(1l,"test",1,1,1));
    }

    private List<CartItem> getCartItemList() {
        return Collections.unmodifiableList(cartItemList);
    }

    // TODO: ДЗ №1
    public void add(Product product) {
        cartItemList.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(), product.getCost()));
    }

    public void recalculate() {
        totalPrice = 0;
        for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice();
        }
    }
}
