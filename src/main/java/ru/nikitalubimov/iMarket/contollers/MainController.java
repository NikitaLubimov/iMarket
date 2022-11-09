package ru.nikitalubimov.iMarket.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.exception.ResourceNotFoundException;
import ru.nikitalubimov.iMarket.services.ProductService;

import java.util.List;


@RestController
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/all")
    public List<Product> getAllProductList() {
        return productService.findAll();
    }

    @GetMapping("/products/getProductById/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }


    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
    }


    @GetMapping("/products/costBetween")
    public List<Product> findAllByCostBetween (@RequestParam (defaultValue = "0") Integer min , @RequestParam (defaultValue = "0") Integer max) {
        return productService.findAllByCostBetween(min, max);
    }

//    @GetMapping("/products/shoppingList/{id}")
//    public List<User> shoppingList(@PathVariable long id) {
//
//    }
}
