package ru.nikitalubimov.iMarket.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void addProduct (Product product) {
        productRepository.save(product);
    }

    public void deleteProductById (long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByCostBetween (Integer min, Integer max ) {
        return productRepository.findAllByCostBetween(min, max);
    }
}
