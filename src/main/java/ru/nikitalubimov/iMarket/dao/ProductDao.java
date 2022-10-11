package ru.nikitalubimov.iMarket.dao;

import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.entity.Product;

import java.util.List;

@Component
public interface ProductDao {

    public void addProduct(Product product);

    public void deleteProductById(Long id);

    public Product findProductById(long id);

    public List<Product> getAllProductList();

}
