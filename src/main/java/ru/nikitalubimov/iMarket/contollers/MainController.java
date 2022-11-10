package ru.nikitalubimov.iMarket.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.dto.ProductDto;
import ru.nikitalubimov.iMarket.exception.ResourceNotFoundException;
import ru.nikitalubimov.iMarket.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProductList() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @PostMapping()
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
    }


    @PutMapping()
    public ProductDto updateProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/costBetween")
    public List<ProductDto> findAllByCostBetween (@RequestParam (defaultValue = "0") Integer min , @RequestParam (defaultValue = "0") Integer max) {
        return productService.findAllByCostBetween(min, max);
    }
}
