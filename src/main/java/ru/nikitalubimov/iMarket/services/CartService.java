package ru.nikitalubimov.iMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.converters.ProductMapper;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.dto.ProductDto;
import ru.nikitalubimov.iMarket.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final ProductMapper MAPPER;

    @Autowired
    private List<Product> productList;
    private static Integer count;


    public List<Product> getProductList() {
        return productList;
    }

    public void addProductCart(Long id) {
        productList.add(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден.")));
        count++;
    }

    public void deleteProductCart(Long id) {
        productList.removeIf(product -> product.getId() == id);
        count--;
    }

}
