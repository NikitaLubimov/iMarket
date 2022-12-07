package ru.nikitalubimov.iMarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.data.Product;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private List<Product> productList;
    private Integer count;

    public void addProductCart(Product product) {
        productList.add(product);
    }

    public void deleteProductCart (Long id) {
        productList.removeIf(product -> product.getId() == id);
    }

}
