package ru.nikitalubimov.iMarket.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.dto.Product;
import ru.nikitalubimov.iMarket.repo.ProductRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getAllProductList() {
     return productRepository.getAllProduct();
    }

    public void addProductRepo(Product product) {
        productRepository.addProduct(product);
    }
}
