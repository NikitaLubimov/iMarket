package ru.nikitalubimov.iMarket.contollers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.converters.ProductMapper;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.dto.ProductDto;
import ru.nikitalubimov.iMarket.exception.ResourceNotFoundException;
import ru.nikitalubimov.iMarket.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper MAPPER;

    @GetMapping()
    public Page<ProductDto> getAllProductList(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minCost,
            @RequestParam(name = "max_price", required = false) Integer maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1 ) {
            page = 1;
        }
        return productService.findAll(minCost, maxCost, page, titlePart);
    }

    @GetMapping("/add/{id}")
    public Product getProductById(@PathVariable long id) {
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

//    @GetMapping("/costBetween")
//    public List<ProductDto> findAllByCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "0") Integer max) {
//        return productService.findAllByCostBetween(min, max);
//    }
}
