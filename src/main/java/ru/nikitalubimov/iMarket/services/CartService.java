package ru.nikitalubimov.iMarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.converters.ProductMapper;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.dto.Cart;
import ru.nikitalubimov.iMarket.dto.CartItem;
import ru.nikitalubimov.iMarket.dto.ProductDto;
import ru.nikitalubimov.iMarket.exception.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private Cart tempCart;
    private final ProductService productService;

    @PostConstruct
    private void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product for cart not found, id: " + productId));
        tempCart.add(product);
    }


//    public void deleteProductCart(Long id) {
//        productList.removeIf(product -> product.getId() == id);
//        count--;
//    }

}
