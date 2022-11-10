package ru.nikitalubimov.iMarket.dto;

import ru.nikitalubimov.iMarket.data.Product;

public class ProductDto {

    private String title;
    private int cost;

    public ProductDto(Product product) {
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

    public ProductDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
