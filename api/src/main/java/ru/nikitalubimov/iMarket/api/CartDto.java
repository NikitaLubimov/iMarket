package ru.nikitalubimov.iMarket.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartDto {

    private List<CartItemDto> cartItemList;
    private int totalPrice;

    public List<CartItemDto> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemDto> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
