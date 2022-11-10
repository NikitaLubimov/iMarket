package ru.nikitalubimov.iMarket.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.dto.ProductDto;
import ru.nikitalubimov.iMarket.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> findAll() {
        List<Product> productRepositoryAll = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product p:productRepositoryAll) {
            productDtoList.add(new ProductDto(p));
        }
        return productDtoList;
    }

    public Optional<ProductDto> findById(Long id) {
        return Optional.of(productRepository.findById(id).map(product -> new ProductDto(product)).orElseThrow());
    }

    public ProductDto addProduct (Product product) {
        Product product1 = productRepository.save(product);
        ProductDto productDto = new ProductDto(product1);
        return productDto;
    }

    public void deleteProductById (long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> findAllByCostBetween (Integer min, Integer max ) {
        List<ProductDto> productDtoList = new ArrayList<>();
        if (min == 0 && max == 0) {
            List<Product> productRepositoryAll = productRepository.findAll();
            for (Product p: productRepositoryAll) {
                productDtoList.add(new ProductDto(p));
            }
            return productDtoList;
        }
        if (min < max) {
            List<Product> productRepositoryBetween = productRepository.findAllByCostBetween(min, max);
            for (Product p: productRepositoryBetween) {
                productDtoList.add(new ProductDto(p));
            }
            return productDtoList;
        } else {
            List<Product> productRepositoryCostBefore = productRepository.findAllByCostBefore(max);
            for (Product p: productRepositoryCostBefore) {
                productDtoList.add(new ProductDto(p));
            }
            return productDtoList;
        }
    }
}
